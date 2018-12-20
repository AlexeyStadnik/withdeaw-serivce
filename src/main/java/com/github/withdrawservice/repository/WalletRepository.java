package com.github.withdrawservice.repository;

import com.github.withdrawservice.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<WalletEntity, Long> {

    /**
     * Lock wallet for update on database level to prevent concurrent updates
     *
     * @param walletId wallet id
     * @return wallet entity
     */
    @Query(value = "SELECT * FROM wallet WHERE id = :walletId FOR UPDATE OF wallet", nativeQuery = true)
    WalletEntity getWalletForUpdate(@Param("walletId") Long walletId);
}
