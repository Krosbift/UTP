package com.santiago.grpc.service;

import com.santiago.grpc.bank.v1.AuthServiceGrpc.AuthServiceImplBase;
import com.santiago.grpc.domain.entity.UserEntity;
import com.santiago.grpc.bank.v1.LoginRequest;
import com.santiago.grpc.bank.v1.LoginResponse;
import com.santiago.grpc.mapper.ProtoMapper;
import com.santiago.grpc.repository.UserRepository;

import com.google.common.base.Optional;

import org.springframework.grpc.server.service.GrpcService;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import lombok.RequiredArgsConstructor;

@GrpcService
@RequiredArgsConstructor
public class AuthService extends AuthServiceImplBase {
    private final UserRepository userRepository;
    private final ProtoMapper mapper;

    @Override
    public void login(
        LoginRequest request,
        StreamObserver<LoginResponse> responseObserver
    ) {

        Optional<UserEntity> userOpt = userRepository.findByEmail(request.getEmail());
        if (!userOpt.isPresent()) {
        responseObserver.onError(
            Status.NOT_FOUND
            .withDescription("Usuario no encontrado")
            .asRuntimeException());
        return;
        }
        UserEntity user = userOpt.get();

        if (!request.getPassword().equals(user.getPassword())) {
        responseObserver.onError(
            Status.PERMISSION_DENIED
            .withDescription("Credenciales inválidas")
            .asRuntimeException());
        return;
        }

        LoginResponse response = LoginResponse.newBuilder()
            .setUser(mapper.toProto(user))
            .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
