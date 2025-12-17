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

        boolean isNameEmpty = dto.getName() == null || dto.getName().trim().isEmpty();
        boolean isYearlyReturnNull = dto.getYearlyReturn() == null;

        if (isNameEmpty && isYearlyReturnNull) {
            throw new IllegalArgumentException(
                    "Minimal salah satu field harus diisi antara name atau yearlyReturn"
            );
        }

        if (!isNameEmpty) {
            old.setName(dto.getName());
        }

        if (!isYearlyReturnNull) {
            old.setYearlyReturn(dto.getYearlyReturn());
        }

        return repo.save(old);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
