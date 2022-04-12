package com.tsapov.bookkeeping.service.impl;

import com.tsapov.bookkeeping.entity.Department;
import com.tsapov.bookkeeping.entity.Employee;
import com.tsapov.bookkeeping.repository.DepartmentRepository;
import com.tsapov.bookkeeping.repository.EmployeeRepository;
import com.tsapov.bookkeeping.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeesServiceImpl implements EmployeesService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Employee> listEmployees() {
        List<Employee> listEmployees = (List<Employee>) employeeRepository.findAll();
        return listEmployees.isEmpty() ? null : listEmployees;
    }

    @Override
    public List<Employee> listByLastName(String lastName) {
        List<Employee> listEmployees = employeeRepository.getAllByLastName(lastName);
        return listEmployees.isEmpty() ? null : listEmployees;
    }

    @Override
    public List<Employee> listByPosition(String position) {
        List<Employee> listEmployees = employeeRepository.getAllByPosition(position);
        return listEmployees.isEmpty() ? null : listEmployees;
    }

    @Override
    public List<Employee> listByDepartment(String depName) {
        List<Employee> listEmployees = employeeRepository.getAllByDepartment_Name(depName);
        return listEmployees.isEmpty() ? null : listEmployees;
    }

    @Override
    public Employee addEmployee(Employee employee, Long depId){
        Department department = departmentRepository.findById(depId).get();
        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee updateEmployeeById(Long id, Long depId, Employee updatedEmployee) {
        Department department = departmentRepository.findById(depId).get();
        Employee employee = employeeRepository.findById(id).get();
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setPatherName(updatedEmployee.getPatherName());
        employee.setPosition(updatedEmployee.getPosition());
        employee.setSalary(updatedEmployee.getSalary());
        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getById(Long id) {
        return employeeRepository.findById(id).get();
    }
}
