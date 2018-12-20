package com.github.withdrawservice.repository;

import com.github.withdrawservice.entity.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Long> {

    BankAccountEntity getBankAccountEntityByUserId(Long userId);
}
