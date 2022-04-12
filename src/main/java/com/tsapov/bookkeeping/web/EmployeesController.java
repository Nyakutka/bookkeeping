package com.tsapov.bookkeeping.web;

import com.tsapov.bookkeeping.entity.Department;
import com.tsapov.bookkeeping.entity.Employee;
import com.tsapov.bookkeeping.service.DepartmentsService;
import com.tsapov.bookkeeping.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/bk/employees")
public class EmployeesController {
    private EmployeesService employeeService;
    private DepartmentsService departmentsService;

    @Autowired
    public void setDepartmentsService(DepartmentsService departmentsService) {
        this.departmentsService = departmentsService;
    }

    @Autowired
    public void setEmployeesService(EmployeesService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/new")
    public String newEmployee(Model model){
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentsService.listDepartments());
        return "employees/new";
    }

    @PostMapping
    public String create(@ModelAttribute("employee") @Valid Employee employee,
                         BindingResult bindingResult,
                         @ModelAttribute("depId") Long depId,
                         Model model){
        model.addAttribute("departments", departmentsService.listDepartments());
        if(bindingResult.hasErrors()){
            return "employees/new";
        }
        employeeService.addEmployee(employee, depId);
        return "redirect:/bk/employees";
    }

    @PostMapping("/{id}/edit")
    public String edit(Model model, @ModelAttribute("id") Long id){
        model.addAttribute("departments", departmentsService.listDepartments());
        model.addAttribute("employee", employeeService.getById(id));
        return "employees/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("employee") @Valid Employee employee,
                         BindingResult bindingResult,
                         @PathVariable("id") Long id,
                         @ModelAttribute("depId") Long depId,
                         Model model){
        model.addAttribute("departments", departmentsService.listDepartments());
        if(bindingResult.hasErrors()){
            return "employees/edit";
        }
        employeeService.updateEmployeeById(id, depId, employee);
        return "redirect:/bk/employees";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("id") Long id){
        employeeService.deleteEmployeeById(id);
        return "redirect:/bk/employees";
    }

    @GetMapping
    public String getAllEmployees(Model model){
        List<Employee> employees = employeeService.listEmployees();
        model.addAttribute("employees", employees);
        model.addAttribute("departments", departmentsService.listDepartments());
        model.addAttribute("title", "Employees");
        return "employees/show";
    }

    @PostMapping("/lastName")
    public String getEmployeesByLastName(@RequestParam String lastName, Model model){
        List<Employee> employees = employeeService.listByLastName(lastName);
        model.addAttribute("employees", employees);
        model.addAttribute("departments", departmentsService.listDepartments());
        model.addAttribute("title", "Last Name: " + lastName);
        return "employees/show";
    }

    @PostMapping("/position")
    public String getEmployeesByPosition(@RequestParam String position, Model model){
        List<Employee> employees = employeeService.listByPosition(position);
        model.addAttribute("employees", employees);
        model.addAttribute("departments", departmentsService.listDepartments());
        model.addAttribute("title", "Position: " + position);
        return "employees/show";
    }

    @PostMapping("/department")
    public String getEmployeesByDepartment(@RequestParam String depName, Model model){
        List<Employee> employees = employeeService.listByDepartment(depName);
        model.addAttribute("employees", employees);
        model.addAttribute("departments", departmentsService.listDepartments());
        model.addAttribute("title", "Department: " + depName);
        return "employees/show";
    }

}
