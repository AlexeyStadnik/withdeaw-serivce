package com.github.withdrawservice.controller;

import com.github.withdrawservice.model.WithdrawModel;
import com.github.withdrawservice.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WithdrawController {

    private final WalletService walletService;

    @Autowired
    public WithdrawController(WalletService walletService) {
        this.walletService = walletService;
    }

    @RequestMapping(value = "/wallets/{walletId}/withdraw",
            produces = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Void> deleteItem(@PathVariable("walletId") Long walletId, @RequestBody WithdrawModel withdrawModel) {
        walletService.withdraw(walletId, withdrawModel);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
