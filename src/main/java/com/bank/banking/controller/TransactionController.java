package com.bank.banking.controller;

import com.bank.banking.entity.Transaction;
import com.bank.banking.repository.TransactionRepository;
import com.bank.banking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionService transactionService;
    @GetMapping("/{accountNumber}")
    public List<Transaction> getTransactions(@PathVariable String accountNumber) {
        return transactionRepository.findByAccountNumber(accountNumber);
    }

    @GetMapping("/miniStatement/{accountNumber}")
    public List<Transaction> miniStatement(@PathVariable String accountNumber) {
        return transactionService.getMiniStatement(accountNumber);
    }
}