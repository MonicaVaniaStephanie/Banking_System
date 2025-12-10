package com.banksaving.banksavingapi.account;

import com.banksaving.banksavingapi.customer.Customer;
import com.banksaving.banksavingapi.depositoType.DepositoType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deposito_type_id", nullable = false)
    private DepositoType depositoType;

    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;
}
