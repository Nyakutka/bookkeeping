package com.tsapov.bookkeeping.service;

import com.tsapov.bookkeeping.entity.Department;
import com.tsapov.bookkeeping.entity.Employee;

import java.util.List;

public interface EmployeesService {
    List<Employee> listEmployees();
    List<Employee> listByLastName(String lastName);
    List<Employee> listByPosition(String position);
    List<Employee> listByDepartment(String depName);
    Employee addEmployee(Employee employees, Long depId);
    void deleteEmployeeById(Long id);
    Employee updateEmployeeById(Long id, Long depId, Employee updatedEmployee);
    Employee getById(Long id);
}
