package com.example.Caching_Application.services.Impl;

import com.example.Caching_Application.entities.Employee;
import com.example.Caching_Application.entities.SalaryAccount;
import com.example.Caching_Application.repositories.SalaryAccountRepository;
import com.example.Caching_Application.services.SalaryAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class SalaryAccountServiceImpl implements SalaryAccountService {

    private final SalaryAccountRepository salaryAccountRepository;

    @Override
    public void createAccount(Employee employee) {

//        if(employee.getName().equals("Anuj"))  throw new RuntimeException("Anuj is not allowed");

        SalaryAccount salaryAccount = SalaryAccount.builder()
                .employee(employee)
                .balance(BigDecimal.ZERO)
                .build();

        salaryAccountRepository.save(salaryAccount);
    }

    @Override
//    @Transactional(isolation = Isolation.SERIALIZABLE)   // remove this line bec we are using optimistic locking
    @Transactional
    public SalaryAccount incrementBalance(Long accountId) {
        SalaryAccount salaryAccount = salaryAccountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Salary account not found with id: " + accountId));

        BigDecimal prevBalance = salaryAccount.getBalance();
        BigDecimal newBalance= prevBalance.add(BigDecimal.valueOf(1L));

        salaryAccount.setBalance(newBalance);

        return salaryAccountRepository.save(salaryAccount);
    }
}
