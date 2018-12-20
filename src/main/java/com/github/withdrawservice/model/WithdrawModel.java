package com.github.withdrawservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode
public class WithdrawModel {

    private UUID requestId;
    private Long withdrawAmount;
}
