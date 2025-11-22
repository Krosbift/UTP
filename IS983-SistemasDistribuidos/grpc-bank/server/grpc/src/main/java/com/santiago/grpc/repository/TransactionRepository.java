package com.santiago.grpc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santiago.grpc.bank.v1.TransactionType;
import com.santiago.grpc.domain.entity.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findByAccountIdOrderByIdAsc(Long accountId);
    List<TransactionEntity> findByType(TransactionType type);
}
