package com.example.dummy.service;

import com.example.dummy.dataAccess.EmployeeAPIService;
import com.example.dummy.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeAPIService employeeAPIService;

    public EmployeeServiceImpl (EmployeeAPIService employeeAPIService) {
        this.employeeAPIService = employeeAPIService;
    }

    public List<EmployeeDTO> getAllEmployees() {

        return employeeAPIService.getAllEmployees();
    }

    public EmployeeDTO getEmployeeById(Long id) {
        Optional<EmployeeDTO> employee = Optional.ofNullable(employeeAPIService.getEmployeeById(id));

        return employee.orElseThrow( () -> new RuntimeException("Employee not found"));
    }

/*
    public Double computeEmployeeSalary (Long employeeId) {
        if()
        Employee employee =
    }

 */

}
