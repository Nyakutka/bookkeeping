package com.tsapov.bookkeeping.service;

import com.tsapov.bookkeeping.entity.Employee;
import com.tsapov.bookkeeping.entity.Project;

import java.util.List;

public interface ProjectsService {
    List<Project> listProjects();
    List<Project> listProjectsByDepartmentName(String depName);
    List<Project> listCurrentProjects();
    List<Project> listCurrentProjectsByDepartmentName(String depName);
    List<Project> listFinishedProjects();
    List<Project> listFinishedProjectsByDepartmentName(String depName);
    Project addProject(Project project, Long depId);
    void deleteProjectById(Long id);
    Project updateProjectById(Long id, Long depId, Project updatedProject);
    Project getById(Long id);
}
