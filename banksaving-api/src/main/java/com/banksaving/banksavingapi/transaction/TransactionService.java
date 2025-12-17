package com.banksaving.banksavingapi.transaction;

import com.banksaving.banksavingapi.account.Account;
import com.banksaving.banksavingapi.account.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepo;
    private final AccountRepository accountRepo;

    public Transaction deposit(Long accountId, Double amountDouble) {

        if (amountDouble == null || amountDouble <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be greater than zero");
        }

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


    public BigDecimal calculateEndingBalance(Long accountId, int days) {

        var account = accountRepo.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        BigDecimal balance = account.getBalance();
        BigDecimal rate = account.getDepositoType().getYearlyReturn();

        BigDecimal interest = balance
                .multiply(rate)
                .multiply(BigDecimal.valueOf(days))
                .divide(BigDecimal.valueOf(365), 2, RoundingMode.HALF_UP);

        return balance.add(interest);
    }

    @Transactional
    public WithdrawResponse withdrawWithEndingBalance(Long accountId, WithdrawRequest req) {

        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        BigDecimal withdrawAmount = req.getAmount();

        if (withdrawAmount == null || withdrawAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be greater than zero");
        }

        BigDecimal startingBalance = account.getBalance();

        if (startingBalance.compareTo(withdrawAmount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        BigDecimal yearlyReturn = account.getDepositoType().getYearlyReturn()
                .divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP);

        BigDecimal monthlyReturn = yearlyReturn
                .divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);

        BigDecimal interestEarned = startingBalance
                .multiply(BigDecimal.valueOf(req.getMonths()))
                .multiply(monthlyReturn)
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal endingBalance = startingBalance
                .add(interestEarned)
                .subtract(withdrawAmount);

        account.setBalance(endingBalance);
        accountRepo.save(account);

        Transaction tx = new Transaction();
        tx.setAccount(account);
        tx.setType(TransactionType.WITHDRAW);
        tx.setAmount(withdrawAmount);
        tx.setDate(LocalDate.now());
        tx.setEndingBalance(endingBalance);
        transactionRepo.save(tx);

        return new WithdrawResponse(
                withdrawAmount,
                interestEarned,
                endingBalance
        );
    }

    public List<Transaction> getHistory(Long accountId) {
        return transactionRepo.findByAccountId(accountId);
    }
}
