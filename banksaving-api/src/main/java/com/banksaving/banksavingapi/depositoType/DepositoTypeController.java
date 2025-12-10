package com.banksaving.banksavingapi.depositoType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deposito-types")
public class DepositoTypeController {

    @Autowired
    private DepositoTypeService service;

    @PostMapping
    public DepositoType create(@RequestBody DepositoType dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<DepositoType> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public DepositoType detail(@PathVariable Long id) {
        return service.find(id);
    }

    @PutMapping("/{id}")
    public DepositoType update(@PathVariable Long id, @RequestBody DepositoType dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
