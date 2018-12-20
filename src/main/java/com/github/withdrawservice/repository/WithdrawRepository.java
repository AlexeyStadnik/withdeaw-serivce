package com.github.withdrawservice.repository;

import com.github.withdrawservice.entity.WithdrawEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawRepository extends JpaRepository<WithdrawEntity, Long> {
}
