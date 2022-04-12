package com.tsapov.bookkeeping.web;

import com.tsapov.bookkeeping.entity.Employee;
import com.tsapov.bookkeeping.entity.Project;
import com.tsapov.bookkeeping.service.DepartmentsService;
import com.tsapov.bookkeeping.service.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/bk/projects")
public class ProjectsController {
    private ProjectsService projectsService;
    private DepartmentsService departmentsService;

    @Autowired
    public void setProjectsService(ProjectsService projectsService) {
        this.projectsService = projectsService;
    }

    @Autowired
    public void setDepartmentsService(DepartmentsService departmentsService) {
        this.departmentsService = departmentsService;
    }

    @GetMapping("/new")
    public String newProject(Model model){
        model.addAttribute("project", new Project());
        model.addAttribute("departments", departmentsService.listDepartments());
        return "projects/new";
    }

    @PostMapping
    public String create(@ModelAttribute("project") @Valid Project project,
                         BindingResult bindingResult,
                         @ModelAttribute("depId") Long depId,
                         Model model){
        model.addAttribute("departments", departmentsService.listDepartments());
        if(bindingResult.hasErrors()){
            return "projects/new";
        }
        projectsService.addProject(project, depId);
        return "redirect:/bk/projects";
    }

    @PostMapping("/{id}/edit")
    public String edit(Model model, @ModelAttribute("id") Long id){
        model.addAttribute("departments", departmentsService.listDepartments());
        model.addAttribute("project", projectsService.getById(id));
        return "projects/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("project") @Valid Project project,
                         BindingResult bindingResult,
                         @PathVariable("id") Long id,
                         @ModelAttribute("depId") Long depId,
                         Model model){
        model.addAttribute("departments", departmentsService.listDepartments());
        if(bindingResult.hasErrors()){
            return "projects/edit";
        }
        projectsService.updateProjectById(id, depId, project);
        return "redirect:/bk/projects";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("id") Long id){
        projectsService.deleteProjectById(id);
        return "redirect:/bk/projects";
    }

    @GetMapping
    public String getAllProjects(Model model){
        List<Project> projects = projectsService.listProjects();
        model.addAttribute("projects", projects);
        model.addAttribute("departments", departmentsService.listDepartments());
        model.addAttribute("title", "Projects");
        return "projects/show";
    }

    @PostMapping("/typeAndDepartment")
    public String getProjects(@RequestParam String type, @RequestParam String depName, Model model){
        List<Project> projects;
        model.addAttribute("departments", departmentsService.listDepartments());
        String title;
        if(depName.equals("all")){
            if(type.equals("current")){
                projects = projectsService.listCurrentProjects();
                title = "Current projects";
            } else{
                projects = projectsService.listFinishedProjects();
                title = "Finished projects";
            }
        } else{
            if(type.equals("all")){
                projects = projectsService.listProjectsByDepartmentName(depName);
                title = depName + " projects";
            }else if(type.equals("current")){
                projects = projectsService.listCurrentProjectsByDepartmentName(depName);
                title = depName + " current projects";
            } else{
                projects = projectsService.listFinishedProjectsByDepartmentName(depName);
                title = depName + " finished projects";
            }
        }
        model.addAttribute("projects", projects);
        model.addAttribute("title", title);
        return "projects/show";
    }

}
