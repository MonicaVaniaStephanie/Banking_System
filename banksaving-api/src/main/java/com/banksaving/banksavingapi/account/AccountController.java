package com.banksaving.banksavingapi.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping
    public Account create(@RequestBody CreateAccountRequest request) {
        return service.create(request);
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

    @PutMapping("/{id}")
    public Account update(
            @PathVariable Long id,
            @RequestBody UpdateAccountRequest request
    ) {
        return service.update(id, request);
    }


}
