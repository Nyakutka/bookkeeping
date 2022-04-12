package com.tsapov.bookkeeping.service.impl;

import com.tsapov.bookkeeping.entity.Department;
import com.tsapov.bookkeeping.repository.DepartmentRepository;
import com.tsapov.bookkeeping.service.DepartmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentsServiceImpl implements DepartmentsService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> listDepartments() {
        List<Department> listDepartments = (List<Department>) departmentRepository.findAll();
        return listDepartments.isEmpty() ? null : listDepartments;
    }

    @Override
    public Department getById(Long id){
        return departmentRepository.findById(id).get();
    }

    @Override
    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartmentById(Long id, Department updatedDepartment) {
        Department department = departmentRepository.findById(id).get();
        department.setName(updatedDepartment.getName());
        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartmentById(Long id) {
        departmentRepository.deleteById(id);
    }

}
