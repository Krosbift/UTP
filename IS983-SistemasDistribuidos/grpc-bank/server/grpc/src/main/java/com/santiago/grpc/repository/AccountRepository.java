package com.santiago.grpc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.common.base.Optional;
import com.santiago.grpc.domain.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByUserId(Long userId);
    List<AccountEntity> findByUserIdIn(List<Long> userIds);
}
