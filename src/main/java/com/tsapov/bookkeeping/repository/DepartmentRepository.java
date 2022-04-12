package com.tsapov.bookkeeping.repository;

import com.tsapov.bookkeeping.entity.Department;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartmentRepository extends CrudRepository<Department, Long> {

}
