package com.banksaving.banksavingapi.account.create;

import com.banksaving.banksavingapi.account.Account;
import com.banksaving.banksavingapi.customer.CustomerRepository;
import com.banksaving.banksavingapi.depositoType.DepositoTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CreateAccountRequestService {

    private final CreateAccountRepository accountRepo;
    private final CustomerRepository customerRepo;
    private final DepositoTypeRepository depositoTypeRepo;

    public Account openAccount(CreateAccountRequest req) {

        var customer = customerRepo.findById(req.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        var depositoType = depositoTypeRepo.findById(req.getDepositoTypeId())
                .orElseThrow(() -> new RuntimeException("Deposito Type not found"));

        BigDecimal initial = req.getInitialDeposit() != null
                ? req.getInitialDeposit()
                : BigDecimal.ZERO;

        Account account = new Account();
        account.setCustomer(customer);
        account.setDepositoType(depositoType);
        account.setBalance(initial);

        return accountRepo.save(account);
    }
}
