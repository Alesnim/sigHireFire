package com.example.sig.util;

import com.example.sig.repository.DepartmentRepo;
import org.springframework.stereotype.Component;

@Component
public class DepartmentCheck {

    private final DepartmentRepo repo;

    public DepartmentCheck(DepartmentRepo repo) {
        this.repo = repo;
    }




}
