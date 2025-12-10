package com.banksaving.banksavingapi.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/deposit")
    public ResponseEntity<Transaction> deposit(@RequestBody Map<String,Object> body) {
        Long accountId = Long.valueOf(body.get("accountId").toString());
        Double amount = Double.valueOf(body.get("amount").toString());
        return ResponseEntity.ok(transactionService.deposit(accountId, amount));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Transaction> withdraw(@RequestBody Map<String,Object> body) {
        Long accountId = Long.valueOf(body.get("accountId").toString());
        Double amount = Double.valueOf(body.get("amount").toString());
        return ResponseEntity.ok(transactionService.withdraw(accountId, amount));
    }

    @GetMapping("/history/{accountId}")
    public ResponseEntity<List<Transaction>> history(@PathVariable Long accountId) {
        return ResponseEntity.ok(transactionService.getHistory(accountId));
    }
}
