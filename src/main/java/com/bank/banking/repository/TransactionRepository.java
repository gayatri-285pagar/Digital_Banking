package com.bank.banking.repository;

import com.bank.banking.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccountNumber(String accountNumber);

    List<Transaction> findTop5ByAccountNumberOrderByDateDesc(String accountNumber);
}