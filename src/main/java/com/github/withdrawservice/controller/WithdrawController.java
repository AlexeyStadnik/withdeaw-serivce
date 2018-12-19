package com.github.withdrawservice.controller;

import com.github.withdrawservice.model.WithdrawModel;
import com.github.withdrawservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@Controller
public class WithdrawController {

    private final AccountService accountService;

    @Autowired
    public WithdrawController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "/accounts/{accountId}/withdraw",
            produces = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Void> deleteItem(@PathVariable("accountId") UUID accountId, @RequestBody WithdrawModel withdrawModel) {
        accountService.withdraw(accountId, withdrawModel);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
