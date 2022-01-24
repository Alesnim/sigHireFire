package com.example.sig.repository;

import com.example.sig.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface EmployersRepo extends CrudRepository<Employee, Long> {

    public List<Employee> findAllByHireTimeBefore(LocalDate date);

    @Query("select d.name from Employee e left join Department d on d.id = e.departmentId.id where e.id = ?1")
    public String getDepartmentNameById(long id);


}
