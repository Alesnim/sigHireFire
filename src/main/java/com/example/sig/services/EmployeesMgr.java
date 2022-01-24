package com.example.sig.services;


import com.example.sig.repository.DepartmentRepo;
import com.example.sig.repository.EmployersRepo;
import com.example.sig.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class EmployeesMgr {

    private DepartmentRepo departmentRepo;

    private EmployersRepo employersRepo;

    private DateUtil dateUtil;

    public EmployeesMgr(DepartmentRepo departmentRepo, EmployersRepo employersRepo, DateUtil dateUtil) {
        this.departmentRepo = departmentRepo;
        this.employersRepo = employersRepo;
        this.dateUtil = dateUtil;
    }


    @Scheduled(fixedDelay = 1000)
    public void hireGeneration() {
        Logger l = LoggerFactory.getLogger(EmployeesMgr.class);
    }
}
