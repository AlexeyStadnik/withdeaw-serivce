package com.github.withdrawservice.service;

import java.util.UUID;

public interface WithdrawService {
    /**
     * Persists the fact of withdraw, helps with request deduplication
     * <p>
     * Could be used as append log for money transfers to check/restore account balance in the given moment of time
     *
     * @param walletId       - wallet money was transferred from
     * @param requestId      - request id
     * @param withdrawAmount - withdraw ammount
     */
    void saveWithdrawLog(Long walletId, UUID requestId, Long withdrawAmount);
}
