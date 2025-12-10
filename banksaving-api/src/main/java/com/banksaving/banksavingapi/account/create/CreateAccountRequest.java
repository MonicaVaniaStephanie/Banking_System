package com.banksaving.banksavingapi.account.create;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateAccountRequest {
    private Long customerId;
    private Long depositoTypeId;
    private BigDecimal initialDeposit;
}
