package com.example.sig.repository;

import com.example.sig.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployersRepo extends CrudRepository<Employee, Long> {
}
