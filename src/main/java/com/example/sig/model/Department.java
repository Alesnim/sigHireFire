package com.example.sig.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
//@Table(name = "Departments")
public class Department {
    @Id
    @GeneratedValue
    private Long id;


    private String name;
}
