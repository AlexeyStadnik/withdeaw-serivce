package com.github.withdrawservice.repository;

import com.github.withdrawservice.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WalletRepository extends JpaRepository<WalletEntity, Long> {

    @Query(value = "SELECT * FROM wallet WHERE id = :walletId FOR UPDATE OF wallet", nativeQuery = true)
    WalletEntity getWalletForUpdate(@Param("walletId") Long walletId);
}
