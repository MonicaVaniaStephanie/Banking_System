package com.banksaving.banksavingapi.depositoType;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "deposito_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositoType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false)
    private BigDecimal yearlyReturn;
}
