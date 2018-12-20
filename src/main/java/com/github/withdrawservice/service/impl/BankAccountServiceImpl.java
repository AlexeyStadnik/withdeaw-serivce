package com.github.withdrawservice.service.impl;

import com.github.withdrawservice.entity.BankAccountEntity;
import com.github.withdrawservice.repository.BankAccountRepository;
import com.github.withdrawservice.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }


    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void withdrawToBankAccount(Long userId, long amount) {
        BankAccountEntity bankAccount = bankAccountRepository.getBankAccountEntityByUserId(userId);
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        bankAccountRepository.save(bankAccount);
    }
}
