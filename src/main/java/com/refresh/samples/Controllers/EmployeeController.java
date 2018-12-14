package com.refresh.samples.Controllers;

import com.refresh.samples.Service.EmployeeService;
import com.refresh.samples.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/app")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/employee/{name}")
    public List<Employee> employeeByLastName(@PathVariable("name") String n){
        return employeeService.employeeByLastName(n.trim());
    }

    @PostMapping(value="/employee",consumes = "application/json")
    public Employee employee(@RequestBody Employee emp) {
        return employeeService.addOrUpdateEmployee(emp);
    }

}
