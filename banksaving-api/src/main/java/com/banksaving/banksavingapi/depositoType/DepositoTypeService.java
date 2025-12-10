package com.banksaving.banksavingapi.depositoType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositoTypeService {

    @Autowired
    private DepositoTypeRepository repo;

    public DepositoType create(DepositoType dto) {
        return repo.save(dto);
    }

    public List<DepositoType> findAll() {
        return repo.findAll();
    }

    public DepositoType find(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Deposito Type not found"));
    }

    public DepositoType update(Long id, DepositoType dto) {
        DepositoType old = find(id);
        old.setName(dto.getName());
        old.setYearlyReturn(dto.getYearlyReturn());
        return repo.save(old);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
