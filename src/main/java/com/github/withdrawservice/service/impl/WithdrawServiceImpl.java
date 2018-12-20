package com.github.withdrawservice.service.impl;

import com.github.withdrawservice.entity.WithdrawEntity;
import com.github.withdrawservice.repository.WithdrawRepository;
import com.github.withdrawservice.service.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class WithdrawServiceImpl implements WithdrawService {

    private final WithdrawRepository withdrawRepository;

    @Autowired
    public WithdrawServiceImpl(WithdrawRepository withdrawRepository) {
        this.withdrawRepository = withdrawRepository;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void saveWithdrawLog(Long walletId, UUID requestId, Long withdrawAmount) {
        withdrawRepository.save(WithdrawEntity.builder()
                .requestId(requestId)
                .walletId(walletId)
                .withdrawAmount(withdrawAmount)
                .build()
        );
    }
}
