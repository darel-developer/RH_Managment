package com.example.darelo.RH.service;

import com.example.darelo.RH.model.Employee;
import com.example.darelo.RH.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepo;

    public EmployeeService(EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public List<Employee> getAllEmployees(){
        return employeeRepo.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id){
        return employeeRepo.findById(id);
    }

    public Employee saveEMployee(Employee employee){
        return employeeRepo.save(employee);
    }

    public void deleteEmployee(Long id){
        employeeRepo.deleteById(id);
    }

    public List<Employee> getActiveEmployee(){
        return employeeRepo.findByActiveTrue();
    }
}
