package com.github.withdrawservice.model;

import lombok.*;

import java.util.UUID;

@Builder
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawModel {

    private UUID requestId;
    private Long withdrawAmount;
}
