package com.refresh.samples.Service;

import com.refresh.samples.Repos.EmployeePublicInfoDTO;
import com.refresh.samples.Repos.EmployeeRepo;
import com.refresh.samples.model.AcmeDBProperties;
import com.refresh.samples.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
public class EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger("EmployeeService");

    @Autowired
    AcmeDBProperties dbProperties;
    @Autowired
    EmployeeRepo employeeRepo;


    public Employee addOrUpdateEmployee(@Valid Employee emp) {
        logger.info("Adding employee...");
        logger.info("First Name:{}, Last Name: {}", emp.getFirstName(), emp.getLastName());
        return employeeRepo.save(emp);

    }

    public List<Employee> employeeByLastName(String lastName){
        logger.info("Finding employee by last name...");
        Collection<EmployeePublicInfoDTO> empsWithLastName = employeeRepo.findByLastName(lastName, EmployeePublicInfoDTO.class);
        return empsWithLastName.stream().map(d -> new Employee(d.getFirstName(),d.getLastName())).collect(Collectors.toList());
    }
}
