package com.github.withdrawservice.repository;

import com.github.withdrawservice.entity.WithdrawEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawRepository extends JpaRepository<WithdrawEntity, Long> {
}
