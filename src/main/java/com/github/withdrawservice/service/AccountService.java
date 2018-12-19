package com.github.withdrawservice.service;

import com.github.withdrawservice.model.WithdrawModel;

import java.util.UUID;

public interface AccountService {
    void withdraw(UUID accountId, WithdrawModel withdrawModel);
}
