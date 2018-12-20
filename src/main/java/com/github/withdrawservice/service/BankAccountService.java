package com.github.withdrawservice.service;

public interface BankAccountService {

    void withdrawToBankAccount(Long userId, long amount);

}
