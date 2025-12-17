package com.banksaving.banksavingapi.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class WithdrawResponse {
    private BigDecimal withdrawnAmount;
    private BigDecimal interestEarned;
    private BigDecimal balanceAfterWithdraw;
}
