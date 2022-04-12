package com.tsapov.bookkeeping.web;

import com.tsapov.bookkeeping.entity.Department;
import com.tsapov.bookkeeping.service.DepartmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/bk/departments")
public class DepartmentsController {
    private DepartmentsService departmentsService;

    @Autowired
    public void setDepartmentsService(DepartmentsService departmentsService) {
        this.departmentsService = departmentsService;
    }

    @GetMapping("/new")
    public String newDepartment(Model model){
        model.addAttribute("department", new Department());
        return "/departments/new";
    }

    @PostMapping
    public String create(@ModelAttribute("department") @Valid Department department,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "departments/new";
        }
        departmentsService.addDepartment(department);
        return "redirect:/bk/departments";
    }

    @PostMapping("/{id}/edit")
    public String edit(Model model, @ModelAttribute("id") Long id){
        model.addAttribute("department", departmentsService.getById(id));
        return "departments/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("department") @Valid Department department,
                         BindingResult bindingResult,
                         @PathVariable("id") Long id){
        if(bindingResult.hasErrors()){
            return "departments/edit";
        }
        departmentsService.updateDepartmentById(id, department);
        return "redirect:/bk/departments";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("id") Long id){
        departmentsService.deleteDepartmentById(id);
        return "redirect:/bk/departments";
    }

    @GetMapping
    public String getAllDepartments(Model model){
        List<Department> departments = departmentsService.listDepartments();
        model.addAttribute("departments", departments);
        model.addAttribute("title", "Departments");
        return "/departments/show";
    }

}
