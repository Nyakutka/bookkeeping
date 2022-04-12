package com.tsapov.bookkeeping.repository;

import com.tsapov.bookkeeping.entity.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    List<Project> getAllByDepartment_Name(String depName);
    List<Project> getAllByDateEndRealIsNull();
    List<Project> getAllByDateEndRealIsNotNull();
    List<Project> getAllByDepartment_NameAndDateEndRealIsNull(String depName);
    List<Project> getAllByDepartment_NameAndDateEndRealIsNotNull(String depName);

}
