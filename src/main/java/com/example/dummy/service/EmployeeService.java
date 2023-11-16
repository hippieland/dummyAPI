package com.example.dummy.service;

import com.example.dummy.dto.EmployeeYearlySalaryDTO;
import com.example.dummy.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(Long id);

    List<EmployeeYearlySalaryDTO> getAllEmployeesWithYearlySalary();

    EmployeeYearlySalaryDTO getEmployeeWithYearlySalaryById (Long employeeId);
}
