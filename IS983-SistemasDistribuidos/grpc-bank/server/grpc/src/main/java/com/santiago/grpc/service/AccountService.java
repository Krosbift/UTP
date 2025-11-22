package com.santiago.grpc.service;

import java.util.List;

import org.springframework.grpc.server.service.GrpcService;

import com.santiago.grpc.bank.v1.AccountServiceGrpc.AccountServiceImplBase;
import com.google.common.base.Optional;
import com.santiago.grpc.bank.v1.CreateAccountRequest;
import com.santiago.grpc.bank.v1.CreateAccountResponse;
import com.santiago.grpc.bank.v1.GetAccountByUserRequest;
import com.santiago.grpc.bank.v1.GetAccountByUserResponse;
import com.santiago.grpc.bank.v1.ListAccountsRequest;
import com.santiago.grpc.bank.v1.ListAccountsResponse;
import com.santiago.grpc.domain.entity.AccountEntity;
import com.santiago.grpc.domain.entity.UserEntity;
import com.santiago.grpc.mapper.ProtoMapper;
import com.santiago.grpc.repository.AccountRepository;
import com.santiago.grpc.repository.UserRepository;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;

@GrpcService
@RequiredArgsConstructor
public class AccountService extends AccountServiceImplBase {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final ProtoMapper mapper;

    @Override
    public void getAccountByUser(
        GetAccountByUserRequest request,
        StreamObserver<GetAccountByUserResponse> responseObserver
    ) {
        Optional<AccountEntity> account = accountRepository.findByUserId(request.getUserId());
        if (!account.isPresent()) {
            responseObserver.onError(
                Status.NOT_FOUND
                .withDescription("Cuenta no encontrada para usuario")
                .asRuntimeException());
            return;
        }
        

        GetAccountByUserResponse response = GetAccountByUserResponse.newBuilder()
                .setAccount(mapper.toProto(account.get()))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void listAccounts(
        ListAccountsRequest request,
        StreamObserver<ListAccountsResponse> responseObserver
    ) {
        List<AccountEntity> accounts;
        if (request.hasUserId()) {
            Optional<AccountEntity> maybeAccount = accountRepository.findByUserId(request.getUserId().getValue());
            if (maybeAccount.isPresent()) {
                accounts = List.of(maybeAccount.get());
            } else {
                accounts = List.of();
            }
        } else {
            accounts = accountRepository.findAll();
        }

        ListAccountsResponse.Builder builder = ListAccountsResponse.newBuilder();
        accounts.forEach(a -> builder.addAccounts(mapper.toProto(a)));

        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }

    @Override
    @Transactional
    public void createAccount(
        CreateAccountRequest request,
        StreamObserver<CreateAccountResponse> responseObserver
    ) {
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> Status.NOT_FOUND
                        .withDescription("Usuario no encontrado")
                        .asRuntimeException());

        AccountEntity account = new AccountEntity();
        account.setUser(user);
        account.setActive(request.hasActive() ? request.getActive().getValue() : true);
        account = accountRepository.save(account);

        CreateAccountResponse response = CreateAccountResponse.newBuilder()
                .setAccount(mapper.toProto(account))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
