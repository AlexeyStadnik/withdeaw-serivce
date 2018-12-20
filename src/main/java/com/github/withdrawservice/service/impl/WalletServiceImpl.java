package com.github.withdrawservice.service.impl;

import com.github.withdrawservice.entity.WalletEntity;
import com.github.withdrawservice.model.WithdrawModel;
import com.github.withdrawservice.repository.WalletRepository;
import com.github.withdrawservice.service.BankAccountService;
import com.github.withdrawservice.service.WalletService;
import com.github.withdrawservice.service.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.github.withdrawservice.WithdrawServiceApplication.BadRequestException;

@Service
public class WalletServiceImpl implements WalletService {

    private final BankAccountService bankAccountService;
    private final WithdrawService withdrawService;
    private final WalletRepository walletRepository;

    @Autowired
    public WalletServiceImpl(BankAccountService bankAccountService,
                             WithdrawService withdrawService,
                             WalletRepository walletRepository) {
        this.bankAccountService = bankAccountService;
        this.withdrawService = withdrawService;
        this.walletRepository = walletRepository;
    }

    @Override
    @Transactional
    public void withdraw(Long walletId, WithdrawModel withdrawModel) {
        WalletEntity wallet = walletRepository.getOne(walletId);

        if (wallet.getBalance() < withdrawModel.getWithdrawAmount()) {
            throw new BadRequestException("Invalid wallet balance");
        }

        wallet.setBalance(wallet.getBalance() - withdrawModel.getWithdrawAmount());
        bankAccountService.withdrawToBankAccount(wallet.getUserId(), withdrawModel.getWithdrawAmount());

        withdrawService.saveWithdrawLog(walletId, withdrawModel.getRequestId(),
                withdrawModel.getWithdrawAmount());
    }

    @Override
    @Transactional
    public WalletEntity retrieveWallet(Long walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(() -> new BadRequestException("No wallet with provided id"));
    }
}
