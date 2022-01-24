package com.example.sig.repository;

import com.example.sig.model.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends CrudRepository<Department, Long> {
}
