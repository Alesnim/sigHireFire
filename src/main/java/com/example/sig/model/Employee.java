package com.example.sig.model;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Data
@Entity
//@Table(name = "Employers")
public class Employee {

    @Id
    @GeneratedValue
    private long id;


    private LocalDate hireTime;

    private LocalDate firedTime;

    // patch error https://stackoverflow.com/questions/6378526/org-hibernate-persistentobjectexception-detached-entity-passed-to-persist
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department departmentId;


    public int getWorkDay(LocalDate now) {
        if (firedTime != null) return Period.between(hireTime, firedTime).getDays();
        else return Period.between(hireTime, now).getDays();
    }
}
