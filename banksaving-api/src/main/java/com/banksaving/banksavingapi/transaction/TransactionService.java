package com.banksaving.banksavingapi.transaction;

import com.banksaving.banksavingapi.account.Account;
import com.banksaving.banksavingapi.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepo;
    private final AccountRepository accountRepo;

    public Transaction deposit(Long accountId, Double amountDouble) {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        BigDecimal amount = BigDecimal.valueOf(amountDouble);
        BigDecimal newBalance = account.getBalance().add(amount);

        account.setBalance(newBalance);
        accountRepo.save(account);

        Transaction t = new Transaction();
        t.setAccount(account);
        t.setType(TransactionType.DEPOSIT);
        t.setAmount(amount);
        t.setDate(LocalDate.now());
        t.setEndingBalance(newBalance);

        return transactionRepo.save(t);
    }

    public Transaction withdraw(Long accountId, Double amountDouble) {

        if (amountDouble == null || amountDouble <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be greater than zero");
        }

        BigDecimal amount = BigDecimal.valueOf(amountDouble);

        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        BigDecimal newBalance = account.getBalance().subtract(amount);

        account.setBalance(newBalance);
        accountRepo.save(account);

        Transaction t = new Transaction();
        t.setAccount(account);
        t.setType(TransactionType.WITHDRAW);
        t.setAmount(amount);
        t.setDate(LocalDate.now());
        t.setEndingBalance(newBalance);

        return transactionRepo.save(t);
    }


    public List<Transaction> getHistory(Long accountId) {
        return transactionRepo.findByAccountId(accountId);
    }
}
