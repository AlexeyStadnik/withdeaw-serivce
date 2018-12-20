package com.github.withdrawservice.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "withdraw")
@Data
@EqualsAndHashCode
@Builder
public class WithdrawEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "request_id", nullable = false, unique = true)
    private UUID requestId;

    @Column(name = "wallet_id", nullable = false)
    private Long walletId;

    @Column(name = "balance", nullable = false)
    private long withdrawAmount;
}
