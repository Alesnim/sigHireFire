package com.example.sig.services;


import com.example.sig.model.Department;
import com.example.sig.model.Employee;
import com.example.sig.repository.DepartmentRepo;
import com.example.sig.repository.EmployersRepo;
import com.example.sig.util.DateUtil;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@EnableScheduling
public class EmployeesMgr {

    private DepartmentRepo departmentRepo;

    private EmployersRepo employersRepo;

    private DateUtil dateUtil;

    private Random random;

    private LocalDate currentDate;

    private long counter = 0;

    private EmployeeHireLogger hireLogger;

    public EmployeesMgr(DepartmentRepo departmentRepo, EmployersRepo employersRepo, DateUtil dateUtil, EmployeeHireLogger hireLogger) {
        this.departmentRepo = departmentRepo;
        this.employersRepo = employersRepo;
        this.dateUtil = dateUtil;
        this.hireLogger = hireLogger;
        this.random = new Random();
        currentDate = dateUtil.getStart();
        counter = dateUtil.getStart().until(dateUtil.getEnd(), ChronoUnit.DAYS);

    }


    @Scheduled(fixedDelay = 1000)
    public void hireGeneration() {
        if (currentDate.isBefore(dateUtil.getEnd())) {

            List<Department> departmentList = new ArrayList<>();
            departmentRepo.findAll().forEach(departmentList::add);
            Employee e = new Employee();
            long days = currentDate.until(dateUtil.getEnd(), ChronoUnit.DAYS);
            int shift = random.nextInt((int) days);
            e.setHireTime(currentDate.plus(Period.ofDays(shift)));
            e.setDepartmentId(departmentList.get(random.nextInt(departmentList.size())));
            e = employersRepo.save(e);

            hireLogger.callbackHire(currentDate, e.getId(), e.getHireTime(),
                    employersRepo.getDepartmentNameById(e.getId()));

            currentDate = currentDate.plus(Period.ofDays(1));
            counter++;
            firePeople();
        }
    }


    public void firePeople() {
        if (counter % 5 == 0) {
            List<Employee> candidateToFire = employersRepo.findAllByHireTimeBefore(currentDate);

            candidateToFire.stream().limit(random.nextInt(4)).forEach(employee -> {
                employee.setFiredTime(currentDate);
                employersRepo.save(employee);
                hireLogger.callbackFire(currentDate,
                        employee.getId(),
                        employee.getHireTime(),
                        employee.getFiredTime(),
                        employersRepo.getDepartmentNameById(employee.getId()));
            });
        }
    }

}
