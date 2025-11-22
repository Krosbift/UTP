package com.santiago.grpc.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.grpc.server.service.GrpcService;

import com.santiago.grpc.bank.v1.AccountRequest;
import com.santiago.grpc.bank.v1.CreateTransactionRequest;
import com.santiago.grpc.bank.v1.CreateTransactionResponse;
import com.santiago.grpc.bank.v1.ListTransactionsRequest;
import com.santiago.grpc.bank.v1.ListTransactionsResponse;
import com.santiago.grpc.bank.v1.Transaction;
import com.santiago.grpc.bank.v1.TransactionServiceGrpc.TransactionServiceImplBase;
import com.santiago.grpc.bank.v1.TransactionType;
import com.santiago.grpc.domain.entity.AccountEntity;
import com.santiago.grpc.domain.entity.TransactionEntity;
import com.santiago.grpc.mapper.ProtoMapper;
import com.santiago.grpc.repository.AccountRepository;
import com.santiago.grpc.repository.TransactionRepository;

import io.grpc.Status;
import io.grpc.stub.ServerCallStreamObserver;
import io.grpc.stub.StreamObserver;

import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;

@GrpcService
@RequiredArgsConstructor
public class TransactionService extends TransactionServiceImplBase {

    private final TransactionRepository txRepository;
    private final AccountRepository accountRepository;
    private final ProtoMapper mapper;
    private final BankService bankService;

    private final Map<Long, List<StreamObserver<Transaction>>> accountStreams = new ConcurrentHashMap<>();

    @Override
    public void listTransactions(
        ListTransactionsRequest request,
        StreamObserver<ListTransactionsResponse> responseObserver
    ) {
        List<TransactionEntity> txs;
        if (request.hasAccountId()) {
            txs = txRepository.findByAccountIdOrderByIdAsc(request.getAccountId().getValue());
        } else {
            txs = Collections.emptyList();
        }

        ListTransactionsResponse.Builder builder = ListTransactionsResponse.newBuilder();
        txs.forEach(tx -> builder.addTransactions(mapper.toProto(tx)));

        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }

    @Override
    @Transactional
    public void createTransaction(
        CreateTransactionRequest request,
        StreamObserver<CreateTransactionResponse> responseObserver
    ) {
        AccountEntity account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> Status.NOT_FOUND
                        .withDescription("Cuenta no encontrada")
                        .asRuntimeException());

        long amount = request.getAmount();
        if (amount <= 0) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription("El monto debe ser positivo")
                    .asRuntimeException());
            return;
        }

        TransactionType typeEntity;
        long signedAmount;
        if (request.getTransactionType() == TransactionType.DEPOSIT) {
            typeEntity = TransactionType.DEPOSIT;
            signedAmount = amount;
        } else if (request.getTransactionType() == TransactionType.WITHDRAW) {
            typeEntity = TransactionType.WITHDRAW;
            signedAmount = -amount;

            long totalAdd = txRepository.findByType(TransactionType.DEPOSIT)
                    .stream()
                    .mapToLong(tx -> tx.getAmount())
                    .sum();

            long totalsSubtract = txRepository.findByType(TransactionType.WITHDRAW)
                    .stream()
                    .mapToLong(tx -> tx.getAmount())
                    .sum();

            if ((totalAdd + totalsSubtract) + signedAmount < 0) {
                responseObserver.onError(Status.FAILED_PRECONDITION
                        .withDescription("Saldo insuficiente")
                        .asRuntimeException());
                return;
            }
        } else {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription("Tipo de transacción inválido")
                    .asRuntimeException());
            return;
        }

        TransactionEntity tx = new TransactionEntity();
        tx.setAccount(account);
        tx.setAmount(signedAmount);
        tx.setType(typeEntity);
        tx.setActive(true);
        tx = txRepository.save(tx);

        Transaction protoTx = mapper.toProto(tx);

        notifyAccountStreams(account.getId(), protoTx);

        // notify bank total streams that the overall total changed
        try {
            bankService.notifyBankTotalChanged();
        } catch (Exception e) {
            // don't fail the RPC if notifying streams fails
        }

        CreateTransactionResponse response = CreateTransactionResponse.newBuilder()
                .setTransaction(protoTx)
                .setAccount(mapper.toProto(account))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void streamAccountTransactions(
        AccountRequest request,
        StreamObserver<Transaction> responseObserver
    ) {
        long accountId = request.getAccountId();

        List<TransactionEntity> txs = txRepository.findByAccountIdOrderByIdAsc(accountId);
        txs.forEach(tx -> responseObserver.onNext(mapper.toProto(tx)));

        // register observer and attach cancel handler so we remove it when client disconnects
        CopyOnWriteArrayList<StreamObserver<Transaction>> list =
            (CopyOnWriteArrayList<StreamObserver<Transaction>>) accountStreams
                .computeIfAbsent(accountId, id -> new CopyOnWriteArrayList<>());

        list.add(responseObserver);

        if (responseObserver instanceof ServerCallStreamObserver) {
            ServerCallStreamObserver<Transaction> serverObs = (ServerCallStreamObserver<Transaction>) responseObserver;
            serverObs.setOnCancelHandler(() -> {
                // remove observer when client cancels to avoid onNext on cancelled streams
                list.remove(serverObs);
            });
        }
    }

    private void notifyAccountStreams(Long accountId, Transaction tx) {
        List<StreamObserver<Transaction>> observers =
                accountStreams.getOrDefault(accountId, List.of());

        // iterate and deliver; skip or remove cancelled observers to avoid exceptions
        for (StreamObserver<Transaction> obs : observers) {
            boolean cancelled = false;
            if (obs instanceof ServerCallStreamObserver) {
                ServerCallStreamObserver<?> s = (ServerCallStreamObserver<?>) obs;
                if (s.isCancelled()) {
                    cancelled = true;
                    // remove from the stored list
                    List<StreamObserver<Transaction>> stored = accountStreams.get(accountId);
                    if (stored instanceof CopyOnWriteArrayList) {
                        ((CopyOnWriteArrayList<StreamObserver<Transaction>>) stored).remove(obs);
                    }
                }
            }

            if (!cancelled) {
                try {
                    obs.onNext(tx);
                } catch (RuntimeException e) {
                    // if sending fails (e.g., call already cancelled), remove observer to avoid future errors
                    List<StreamObserver<Transaction>> stored = accountStreams.get(accountId);
                    if (stored instanceof CopyOnWriteArrayList) {
                        ((CopyOnWriteArrayList<StreamObserver<Transaction>>) stored).remove(obs);
                    }
                }
            }
        }
    }
}

