package com.example.sig.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

@Component
@EnableAsync
public class EmployeeHireLogger {
    Logger l = LoggerFactory.getLogger(EmployeesMgr.class);

    @Async
    public void callbackHire(LocalDate now, long id, LocalDate hireDate, String departmentName) {
        String res = String.format("Сотрудник %d нанят %s. Отдел: %s", id, hireDate, departmentName);
        l.info(res);
    }


    @Async
    public void callbackFire(LocalDate now, long id, LocalDate hireDate, LocalDate fireDate, String deparmentName) {
        String res = String.format("Сотрудник %d уволен %s. Отдел: %s. Проработал: %d", id, fireDate, deparmentName, hireDate.until(fireDate, ChronoUnit.DAYS));
        l.info(res);
    }
}
