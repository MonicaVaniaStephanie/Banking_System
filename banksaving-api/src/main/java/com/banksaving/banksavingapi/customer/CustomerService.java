package com.banksaving.banksavingapi.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer create(Customer customer){
        return customerRepository.save(customer);
    }

    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

    public Customer update(Long id, Customer updated){
        Customer c = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        c.setName(updated.getName());
        return customerRepository.save(c);
    }

    public void delete(Long id){
        customerRepository.deleteById(id);
    }
}
