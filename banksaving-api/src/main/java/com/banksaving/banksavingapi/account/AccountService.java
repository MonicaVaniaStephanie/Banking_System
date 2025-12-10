package com.banksaving.banksavingapi.account;

import com.banksaving.banksavingapi.customer.Customer;
import com.banksaving.banksavingapi.customer.CustomerRepository;
import com.banksaving.banksavingapi.depositoType.DepositoType;
import com.banksaving.banksavingapi.depositoType.DepositoTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repo;

    @Autowired
    private CustomerRepository customerRepo;
    
    @Autowired
    private DepositoTypeRepository depositoRepo;

    public Account create(Long customerId, Long depositoTypeId) {

        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        DepositoType depositoType = depositoRepo.findById(depositoTypeId)
                .orElseThrow(() -> new RuntimeException("Deposito Type not found"));

        Account account = new Account();
        account.setCustomer(customer);
        account.setDepositoType(depositoType);
        account.setBalance(BigDecimal.ZERO);

        return repo.save(account);
    }

    public Account find(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public List<Account> findAll() {
        return repo.findAll();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
