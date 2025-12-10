package com.banksaving.banksavingapi.account;

import com.banksaving.banksavingapi.account.create.CreateAccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping
    public Account create(@RequestParam Long customerId,
                          @RequestParam Long depositoTypeId) {
        return service.create(customerId, depositoTypeId);
    }

    @GetMapping("/{id}")
    public Account find(@PathVariable Long id) {
        return service.find(id);
    }

    @GetMapping
    public List<Account> list() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
