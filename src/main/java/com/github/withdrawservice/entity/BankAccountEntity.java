package com.github.withdrawservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "bank_account")
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "balance", nullable = false)
    private long balance = 0L;

}
