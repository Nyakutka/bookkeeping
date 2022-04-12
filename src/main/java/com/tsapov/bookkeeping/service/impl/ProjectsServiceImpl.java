package com.tsapov.bookkeeping.service.impl;

import com.tsapov.bookkeeping.entity.Department;
import com.tsapov.bookkeeping.entity.Employee;
import com.tsapov.bookkeeping.entity.Project;
import com.tsapov.bookkeeping.repository.DepartmentRepository;
import com.tsapov.bookkeeping.repository.ProjectRepository;
import com.tsapov.bookkeeping.service.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectsServiceImpl implements ProjectsService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Project> listProjects() {
        List<Project> listProjects = (List<Project>) projectRepository.findAll();
        return listProjects.isEmpty() ? null : listProjects;
    }

    @Override
    public List<Project> listProjectsByDepartmentName(String depName){
        List<Project> listProjects = projectRepository.getAllByDepartment_Name(depName);
        return listProjects.isEmpty() ? null : listProjects;
    }

    @Override
    public List<Project> listCurrentProjects(){
        List<Project> listProjects = projectRepository.getAllByDateEndRealIsNull();
        return listProjects.isEmpty() ? null : listProjects;
    }

    @Override
    public List<Project> listCurrentProjectsByDepartmentName(String depName){
        List<Project> listProjects = projectRepository.getAllByDepartment_NameAndDateEndRealIsNull(depName);
        return listProjects.isEmpty() ? null : listProjects;
    }

    @Override
    public List<Project> listFinishedProjects(){
        List<Project> listProjects = projectRepository.getAllByDateEndRealIsNotNull();
        return listProjects.isEmpty() ? null : listProjects;
    }

    @Override
    public List<Project> listFinishedProjectsByDepartmentName(String depName){
        List<Project> listProjects = projectRepository.getAllByDepartment_NameAndDateEndRealIsNotNull(depName);
        return listProjects.isEmpty() ? null : listProjects;
    }

    @Override
    public Project addProject(Project project, Long depId){
        Department department = departmentRepository.findById(depId).get();
        project.setDepartment(department);
        return projectRepository.save(project);
    }

    @Override
    public void deleteProjectById(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Project updateProjectById(Long id, Long depId, Project updatedProject) {
        Department department = departmentRepository.findById(depId).get();
        Project project = projectRepository.findById(id).get();
        project.setName(updatedProject.getName());
        project.setCost(updatedProject.getCost());
        project.setDateBeg(updatedProject.getDateBeg());
        project.setDateEnd(updatedProject.getDateEnd());
        project.setDateEndReal(updatedProject.getDateEndReal());
        project.setDepartment(department);
        return projectRepository.save(project);
    }

    @Override
    public Project getById(Long id) {
        return projectRepository.findById(id).get();
    }
}
