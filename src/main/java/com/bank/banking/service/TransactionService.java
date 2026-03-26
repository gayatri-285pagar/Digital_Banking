package com.bank.banking.service;

import com.bank.banking.entity.Transaction;
import com.bank.banking.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getMiniStatement(String accountNumber) {
        return transactionRepository
                .findTop5ByAccountNumberOrderByDateDesc(accountNumber);
    }
}