package com.banksaving.banksavingapi.account.create;

import com.banksaving.banksavingapi.account.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/new-account")
@RequiredArgsConstructor
public class CreateAccountController {

    private final CreateAccountRequestService accountRequestService;

    @PostMapping("/open")
    public Account openAccount(@RequestBody CreateAccountRequest request) {
        return accountRequestService.openAccount(request);
    }
}
