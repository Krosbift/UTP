package com.santiago.grpc.service;

import com.santiago.grpc.bank.v1.CreateUserRequest;
import com.santiago.grpc.bank.v1.CreateUserResponse;
import com.santiago.grpc.bank.v1.GetUserRequest;
import com.santiago.grpc.bank.v1.GetUserResponse;
import com.santiago.grpc.bank.v1.ListUsersRequest;
import com.santiago.grpc.bank.v1.ListUsersResponse;
import com.santiago.grpc.bank.v1.UserServiceGrpc.UserServiceImplBase;
import com.santiago.grpc.domain.entity.AccountEntity;
import com.santiago.grpc.domain.entity.UserEntity;
import com.santiago.grpc.mapper.ProtoMapper;
import com.santiago.grpc.repository.AccountRepository;
import com.santiago.grpc.repository.UserRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.grpc.server.service.GrpcService;

import java.util.List;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import lombok.RequiredArgsConstructor;

@GrpcService
@RequiredArgsConstructor
public class UserService extends UserServiceImplBase {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final ProtoMapper mapper;

    @Override
    @Transactional
    public void createUser(
        CreateUserRequest request,
        StreamObserver<CreateUserResponse> responseObserver
    ) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            responseObserver.onError(
                Status.ALREADY_EXISTS
                .withDescription("Email ya registrado")
                .asRuntimeException());
            return;
        }

        UserEntity user = new UserEntity();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(request.getPassword());
        user.setActive(true);
        user = userRepository.save(user);

        AccountEntity account = new AccountEntity();
        account.setUser(user);
        account.setActive(true);
        accountRepository.save(account);

        CreateUserResponse response = CreateUserResponse.newBuilder()
        .setUser(mapper.toProto(user))
        .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void listUsers(
        ListUsersRequest request,
        StreamObserver<ListUsersResponse> responseObserver
    ) {
        List<UserEntity> entities = userRepository.findAll();

        ListUsersResponse.Builder builder = ListUsersResponse.newBuilder();
        
        entities.forEach(entity -> builder.addUsers(mapper.toProto(entity)));
        
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void getUser(
        GetUserRequest request,
        StreamObserver<GetUserResponse> responseObserver
    ) {
        UserEntity user = userRepository.findById(request.getId())
            .orElseThrow(() -> 
            Status.NOT_FOUND.withDescription("Usuario no encontrado")
            .asRuntimeException());

        GetUserResponse response = GetUserResponse.newBuilder()
            .setUser(mapper.toProto(user))
            .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
