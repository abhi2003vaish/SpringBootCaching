package com.example.Caching_Application.services;

import com.example.Caching_Application.entities.Employee;
import com.example.Caching_Application.entities.SalaryAccount;

public interface SalaryAccountService {
    void createAccount(Employee employee);

    SalaryAccount incrementBalance(Long accountId);
}
