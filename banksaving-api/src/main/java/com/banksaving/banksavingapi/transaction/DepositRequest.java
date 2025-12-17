package com.banksaving.banksavingapi.transaction;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DepositRequest {
    private Long accountId;
    private Double amount;
    private LocalDate transactionDate;
}
