package com.banksaving.banksavingapi.account.create;

import com.banksaving.banksavingapi.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreateAccountRepository extends JpaRepository<Account, Long> {
}

