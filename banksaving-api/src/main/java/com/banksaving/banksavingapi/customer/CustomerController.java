package com.banksaving.banksavingapi.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public Customer create(@RequestBody Customer customer){
        return customerService.create(customer);
    }

    @GetMapping
    public List<Customer> getAll(){
        return customerService.getAll();
    }

    @PutMapping("/{id}")
    public Customer update(@PathVariable Long id, @RequestBody Customer customer){
        return customerService.update(id, customer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        customerService.delete(id);
    }
}
