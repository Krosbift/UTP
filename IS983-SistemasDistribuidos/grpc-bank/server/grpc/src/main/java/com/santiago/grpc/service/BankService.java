package com.santiago.grpc.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.grpc.server.service.GrpcService;

import com.google.protobuf.Empty;
import com.santiago.grpc.bank.v1.BankServiceGrpc.BankServiceImplBase;
import com.santiago.grpc.bank.v1.BankTotal;
import com.santiago.grpc.bank.v1.BankTotalUpdate;
import com.santiago.grpc.bank.v1.TransactionType;
import com.santiago.grpc.repository.TransactionRepository;

import io.grpc.stub.ServerCallStreamObserver;
import io.grpc.stub.StreamObserver;

import lombok.RequiredArgsConstructor;

@GrpcService
@RequiredArgsConstructor
public class BankService extends BankServiceImplBase {
    private final TransactionRepository transactionRepository;
    private final List<StreamObserver<BankTotalUpdate>> bankStreams = new CopyOnWriteArrayList<>();

    @Override
    public void getBankTotal(
        Empty request,
        StreamObserver<BankTotal> responseObserver
    ) {
        long totalAdd = transactionRepository.findByType(TransactionType.DEPOSIT)
                .stream()
                .mapToLong(tx -> tx.getAmount())
                .sum();

        long totalsSubtract = transactionRepository.findByType(TransactionType.WITHDRAW)
                .stream()
                .mapToLong(tx -> tx.getAmount())
                .sum();

        
        BankTotal response = BankTotal.newBuilder()
                .setTotalAmount(totalAdd + totalsSubtract)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void streamBankTotal(
        Empty request,
        StreamObserver<BankTotalUpdate> responseObserver
    ) {
                bankStreams.add(responseObserver);
                if (responseObserver instanceof ServerCallStreamObserver) {
                        ServerCallStreamObserver<BankTotalUpdate> serverObs = (ServerCallStreamObserver<BankTotalUpdate>) responseObserver;
                        serverObs.setOnCancelHandler(() -> bankStreams.remove(serverObs));
                }

        long totalAdd = transactionRepository.findByType(TransactionType.DEPOSIT)
                .stream()
                .mapToLong(tx -> tx.getAmount())
                .sum();

        long totalsSubtract = transactionRepository.findByType(TransactionType.WITHDRAW)
                .stream()
                .mapToLong(tx -> tx.getAmount())
                .sum();

        BankTotalUpdate init = BankTotalUpdate.newBuilder()
                .setTotalAmount(totalAdd + totalsSubtract)
                .build();
        responseObserver.onNext(init);
    }

    public void notifyBankTotalChanged() {
        long totalAdd = transactionRepository.findByType(TransactionType.DEPOSIT)
                .stream()
                .mapToLong(tx -> tx.getAmount())
                .sum();

        long totalsSubtract = transactionRepository.findByType(TransactionType.WITHDRAW)
                .stream()
                .mapToLong(tx -> tx.getAmount())
                .sum();

        BankTotalUpdate update = BankTotalUpdate.newBuilder()
                .setTotalAmount(totalAdd + totalsSubtract)
                .build();
                for (StreamObserver<BankTotalUpdate> obs : bankStreams) {
                        boolean cancelled = false;
                        if (obs instanceof ServerCallStreamObserver) {
                                ServerCallStreamObserver<?> s = (ServerCallStreamObserver<?>) obs;
                                if (s.isCancelled()) {
                                        cancelled = true;
                                        bankStreams.remove(obs);
                                }
                        }

                        if (!cancelled) {
                                try {
                                        obs.onNext(update);
                                } catch (RuntimeException e) {
                                        // remove observers that error to avoid repeated exceptions
                                        bankStreams.remove(obs);
                                }
                        }
                }
    }
}

