package com.example.dummy.controller;

import com.example.dummy.dto.EmployeeYearlySalaryDTO;
import com.example.dummy.dto.EmployeeDTO;
import com.example.dummy.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("employee/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeeId) {
        EmployeeDTO retrievedEmployee = employeeService.getEmployeeById(employeeId);
        if(retrievedEmployee != null)
            return ResponseEntity.ok(retrievedEmployee);
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("employees/yearlySalary")
    public List<EmployeeYearlySalaryDTO> getAllEmployeesWithYearlySalary() {
        return employeeService.getAllEmployeesWithYearlySalary();
    }

    @GetMapping("employee/yearlySalary/{employeeId}")
    public ResponseEntity<EmployeeYearlySalaryDTO> getEmployeeWithYearlySalaryById(@PathVariable Long employeeId) {
        EmployeeYearlySalaryDTO retrievedEmployee = employeeService.getEmployeeWithYearlySalaryById(employeeId);
        if(retrievedEmployee != null)
            return ResponseEntity.ok(retrievedEmployee);
        else
            return ResponseEntity.notFound().build();
    }

}
