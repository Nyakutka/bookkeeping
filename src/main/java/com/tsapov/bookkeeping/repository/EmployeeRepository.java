package com.tsapov.bookkeeping.repository;

import com.tsapov.bookkeeping.entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> getAllByLastName(String lastName);
    List<Employee> getAllByPosition(String position);
    List<Employee> getAllByDepartment_Name(String depName);
}
