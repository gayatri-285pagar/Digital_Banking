package com.bank.banking.service;

import com.bank.banking.entity.Account;
import com.bank.banking.entity.Transaction;
import com.bank.banking.entity.TransactionType;
import com.bank.banking.entity.User;
import com.bank.banking.repository.AccountRepository;
import com.bank.banking.repository.TransactionRepository;
import com.bank.banking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bank.banking.dto.TransferRequest;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    // Create Account
    public Account createAccount(Long userId) {

        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return null;
        }

        Account account = new Account();
        account.setUser(user);
        account.setBalance(0.0);

        // generate random account number
        String accNumber = "ACC" + new Random().nextInt(100000);
        account.setAccountNumber(accNumber);

        return accountRepository.save(account);
    }

    // Deposit Money
    public Account deposit(String accountNumber, double amount) {

        // 1. Find account
        Account account = accountRepository.findByAccountNumber(accountNumber);

        if (account == null) {
            return null;
        }

        // 2. Add balance
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        // 3. Create transaction
        Transaction txn = new Transaction();
        txn.setAccountNumber(accountNumber);
        txn.setType(TransactionType.DEPOSIT);
        txn.setAmount(amount);
        txn.setDate(LocalDateTime.now());

        // 4. Save transaction
        transactionRepository.save(txn);

        return account;
    }


    // Withdraw Money
    public String withdraw(String accountNumber, double amount) {

        // 1. Find account
        Account account = accountRepository.findByAccountNumber(accountNumber);

        if (account == null) {
            return "Account not found";
        }

        // 2. Check balance
        if (account.getBalance() < amount) {
            return "Insufficient Balance";
        }

        // 3. Deduct balance
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        // 4. Create transaction
        Transaction txn = new Transaction();
        txn.setAccountNumber(accountNumber);
        txn.setType(TransactionType.WITHDRAW);
        txn.setAmount(amount);
        txn.setDate(LocalDateTime.now());

        // 5. Save transaction
        transactionRepository.save(txn);

        return "Withdrawal Successful";
    }

    public String transfer(TransferRequest request) {

        Account from = accountRepository.findByAccountNumber(request.getFromAccount());
        Account to = accountRepository.findByAccountNumber(request.getToAccount());

        if (from == null || to == null) {
            throw new RuntimeException("Account not found");
        }

        if (from.getBalance() < request.getAmount()) {
            throw new RuntimeException("Insufficient balance");
        }

        // 💸 Deduct from sender
        from.setBalance(from.getBalance() - request.getAmount());

        // 💰 Add to receiver
        to.setBalance(to.getBalance() + request.getAmount());

        accountRepository.save(from);
        accountRepository.save(to);

        // 🔥 SAVE TRANSACTIONS (IMPORTANT)

        Transaction t1 = new Transaction();
        t1.setAccountNumber(request.getFromAccount());
        t1.setAmount(request.getAmount());
        t1.setType(TransactionType.WITHDRAW);
        t1.setDate(LocalDateTime.now());

        Transaction t2 = new Transaction();
        t2.setAccountNumber(request.getToAccount());
        t2.setAmount(request.getAmount());
        t2.setType(TransactionType.DEPOSIT);
        t2.setDate(LocalDateTime.now());

        transactionRepository.save(t1);
        transactionRepository.save(t2);

        return "Transfer Successful";
    }
}