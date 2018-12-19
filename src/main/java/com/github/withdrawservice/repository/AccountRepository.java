package com.github.withdrawservice.repository;

import com.github.withdrawservice.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository  extends JpaRepository<AccountEntity, UUID> {
}
