package com.github.withdrawservice.service.impl;

import com.github.withdrawservice.entity.AccountEntity;
import com.github.withdrawservice.model.WithdrawModel;
import com.github.withdrawservice.repository.AccountRepository;
import com.github.withdrawservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.UUID;

public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public void withdraw(UUID accountId, WithdrawModel withdrawModel) {
        //TODO should block account
        AccountEntity account = accountRepository.getOne(accountId);

        if(account.getBalance() < withdrawModel.getWithdrawAmount()) {
            throw new RuntimeException("Invalid account balance");
        }

        account.setBalance(account.getBalance() - withdrawModel.getWithdrawAmount());
        //TODO append only log of withdraws

    }
}
