package com.banksaving.banksavingapi.transaction;

import com.banksaving.banksavingapi.account.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Account account;

    @Enumerated(EnumType.STRING)
    private TransactionType type; // DEPOSIT / WITHDRAW (only 2 ya)

    @Column(nullable = false)
    private BigDecimal amount;

    private LocalDate date;

    private BigDecimal endingBalance;
}
