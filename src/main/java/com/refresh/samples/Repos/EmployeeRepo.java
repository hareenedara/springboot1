package com.refresh.samples.Repos;

import com.refresh.samples.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface EmployeeRepo extends CrudRepository<Employee,Long> {

    <T> Collection<T> findByLastName(String lastName, Class<T> type);
}
