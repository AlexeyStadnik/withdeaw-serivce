package com.github.withdrawservice.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "account")
@Data
@EqualsAndHashCode
public class AccountEntity {

    @Id
    @GeneratedValue
    @Type(type = "pg-uuid")
    private UUID id;

    @Column(name = "balance")
    private Long balance;

}
