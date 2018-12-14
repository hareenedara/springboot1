package com.refresh.samples.model;

import com.refresh.samples.model.Employee;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EmpValidError {
    private Employee employee;
    private String status;
    private List<String> errorMessages=new ArrayList<>();
    private String path;
}
