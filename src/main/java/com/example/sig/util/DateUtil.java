package com.example.sig.util;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@ConfigurationProperties(prefix = "sig")
public class DateUtil {
    private String startDate;
    private String endDate;
    private LocalDate start;
    private LocalDate end;

    public LocalDate getStart() {
        if (null == start) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            start = LocalDate.parse(startDate, formatter);
        }
        return start;
    }

    public LocalDate getEnd() {
        if (null == end) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            end = LocalDate.parse(endDate, formatter);
        }
        return end;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
