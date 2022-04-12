package com.tsapov.bookkeeping.service;

import com.tsapov.bookkeeping.entity.Department;

import java.util.List;

public interface DepartmentsService {
    List<Department> listDepartments();
    Department addDepartment(Department department);
    void deleteDepartmentById(Long id);
    Department updateDepartmentById(Long id, Department updatedDepartment);
    Department getById(Long id);
}
