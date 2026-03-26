package com.bank.banking.controller;

import com.bank.banking.dto.TransferRequest;
import com.bank.banking.entity.Account;
import com.bank.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/create/{userId}")
    public Account createAccount(@PathVariable Long userId) {
        return accountService.createAccount(userId);
    }

    // Deposit API
    @PostMapping("/deposit")
    public Account deposit(@RequestParam String accountNumber,
                           @RequestParam double amount) {
        return accountService.deposit(accountNumber, amount);
    }

    // Withdraw API
    @PostMapping("/withdraw")
    public String withdraw(@RequestParam String accountNumber,
                           @RequestParam double amount) {
        return accountService.withdraw(accountNumber, amount);
    }

    @PostMapping("/transfer")
    public String transfer(@RequestBody TransferRequest request) {
        return accountService.transfer(request);
    }
}