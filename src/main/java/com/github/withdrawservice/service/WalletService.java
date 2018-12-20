package com.github.withdrawservice.service;

import com.github.withdrawservice.entity.WalletEntity;
import com.github.withdrawservice.model.WithdrawModel;

public interface WalletService {
    void withdraw(Long accountId, WithdrawModel withdrawModel);

    WalletEntity retrieveWallet(Long walletId);
}
