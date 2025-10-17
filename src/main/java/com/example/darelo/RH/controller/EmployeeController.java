package com.example.darelo.RH.controller;

import com.example.darelo.RH.model.Employee;
import com.example.darelo.RH.repository.DepartmentRepository;
import com.example.darelo.RH.repository.PositionRepository;
import com.example.darelo.RH.service.EmployeeService;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final DepartmentRepository departmentRepo;
    private  final PositionRepository positionRepo;

    public EmployeeController(EmployeeService employeeService, DepartmentRepository departmentRepo, PositionRepository positionRepo) {
        this.employeeService = employeeService;
        this.departmentRepo = departmentRepo;
        this.positionRepo = positionRepo;
    }

    @GetMapping("/new")
    public String showCreateForm(Model model){
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentRepo.findAll());
        model.addAttribute("positions", positionRepo.findAll());
        return "empoyee-form";
    }

    @GetMapping
    public String listEmployees(Model model){
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employees";
    }

    @PostMapping
    public String saveEmployee(@ModelAttribute Employee employee){
        employeeService.saveEMployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model){
        Employee employee = employeeService.getEmployeeById(id).orElseThrow();
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departmentRepo.findAll());
        model.addAttribute("positions", positionRepo.findAll());
        return "employee-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return "redirect:/employess";
    }

    @GetMapping("/{id}")
    public String viewEmployee(@PathVariable Long id, Model model){
        model.addAttribute("employee", employeeService.getEmployeeById(id).orElseThrow());
        return "employee-details";
    }
}
