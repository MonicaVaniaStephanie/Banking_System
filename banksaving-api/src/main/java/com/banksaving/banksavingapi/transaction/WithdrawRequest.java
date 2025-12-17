package com.banksaving.banksavingapi.transaction;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WithdrawRequest {
    private BigDecimal amount;
    private int months;
}
