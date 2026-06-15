package com.example.Caching_Application.repositories;

import com.example.Caching_Application.entities.SalaryAccount;
import org.springframework.data.repository.CrudRepository;

public interface SalaryAccountRepository extends CrudRepository<SalaryAccount,Long> {
}
