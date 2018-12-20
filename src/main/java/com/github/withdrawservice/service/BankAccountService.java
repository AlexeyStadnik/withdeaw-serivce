package com.github.withdrawservice.service;

import java.util.UUID;

public interface BankAccountService {

    void withdrawToBankAccount(Long userId, long amount);

}
