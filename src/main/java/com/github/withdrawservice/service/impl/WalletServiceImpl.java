package com.github.withdrawservice.service.impl;

import com.github.withdrawservice.entity.WalletEntity;
import com.github.withdrawservice.model.WithdrawModel;
import com.github.withdrawservice.repository.WalletRepository;
import com.github.withdrawservice.service.BankAccountService;
import com.github.withdrawservice.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.github.withdrawservice.WithdrawServiceApplication.BadRequestException;

@Service
public class WalletServiceImpl implements WalletService {

    private final BankAccountService bankAccountService;
    private final WalletRepository walletRepository;

    @Autowired
    public WalletServiceImpl(BankAccountService bankAccountService,
                             WalletRepository walletRepository) {
        this.bankAccountService = bankAccountService;
        this.walletRepository = walletRepository;
    }

    @Override
    @Transactional
    public void withdraw(Long accountId, WithdrawModel withdrawModel) {
        WalletEntity wallet = walletRepository.getOne(accountId);

        if (wallet.getBalance() < withdrawModel.getWithdrawAmount()) {
            throw new BadRequestException("Invalid wallet balance");
        }

        wallet.setBalance(wallet.getBalance() - withdrawModel.getWithdrawAmount());
        bankAccountService.withdrawToBankAccount(wallet.getUserId(), withdrawModel.getWithdrawAmount());
    }
}
