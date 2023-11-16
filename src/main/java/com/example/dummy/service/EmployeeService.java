package com.example.dummy.service;

import com.example.dummy.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    public List<EmployeeDTO> getAllEmployees();

    public EmployeeDTO getEmployeeById(Long id);
    //public Double computeEmployeeSalary (Employee employee);
}
