package com.example.sig.model;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
//@Table(name = "Employers")
public class Employee {

    @Id
    @GeneratedValue
    private long id;


    private LocalDate hireTime;

    private LocalDate firedTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department departmentId;
}
