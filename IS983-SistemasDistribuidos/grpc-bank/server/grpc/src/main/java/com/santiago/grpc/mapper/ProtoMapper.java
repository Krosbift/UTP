package com.santiago.grpc.mapper;

import org.springframework.stereotype.Component;

import com.santiago.grpc.bank.v1.Account;
import com.santiago.grpc.bank.v1.Transaction;
import com.santiago.grpc.bank.v1.TransactionType;
import com.santiago.grpc.bank.v1.User;
import com.santiago.grpc.domain.entity.AccountEntity;
import com.santiago.grpc.domain.entity.TransactionEntity;
import com.santiago.grpc.domain.entity.UserEntity;

@Component
public class ProtoMapper {
    public User toProto(UserEntity entity) {
        return User.newBuilder()
            .setId(entity.getId())
            .setEmail(entity.getEmail())
            .setName(entity.getName())
            .setActive(entity.isActive())
            .build();
    }

    public Account toProto(AccountEntity entity) {
        return Account.newBuilder()
            .setId(entity.getId())
            .setUserId(entity.getUser().getId())
            .setActive(entity.isActive())
            .build();
    }

    public Transaction toProto(TransactionEntity entity) {
        TransactionType type = entity.getType() == TransactionType.DEPOSIT
            ? TransactionType.DEPOSIT
            : TransactionType.WITHDRAW;

            return Transaction.newBuilder()
            .setId(entity.getId())
            .setAccountId(entity.getAccount().getId())
            .setTransactionType(type)
            .setAmount(entity.getAmount())
            .setActive(entity.isActive())
            .build();
    }
}
