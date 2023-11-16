package com.example.dummy.service;

import com.example.dummy.dataAccess.EmployeeAPIService;
import com.example.dummy.dto.EmployeeYearlySalaryDTO;
import com.example.dummy.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<EmployeeYearlySalaryDTO> getAllEmployeesWithYearlySalary() {
        List<EmployeeYearlySalaryDTO> employeesWithYearlySalary = new ArrayList<>();
        for(EmployeeDTO fetchedEmployee : employeeAPIService.getAllEmployees()){
            Double employee_yearly_salary = fetchedEmployee.getEmployee_salary() * 12;
            EmployeeYearlySalaryDTO employeeYearlySalaryDTO = new EmployeeYearlySalaryDTO(fetchedEmployee.getId(), fetchedEmployee.getEmployee_name(), fetchedEmployee.getEmployee_salary(), fetchedEmployee.getEmployee_age(), fetchedEmployee.getProfile_image(), employee_yearly_salary);
            employeesWithYearlySalary.add(employeeYearlySalaryDTO);
        }
        return employeesWithYearlySalary;
    }


    public EmployeeYearlySalaryDTO getEmployeeWithYearlySalaryById (Long employeeId) {
        if(this.getEmployeeById(employeeId) != null) {
            EmployeeDTO fetchedEmployee = this.getEmployeeById(employeeId);
            Double employee_yearly_salary = fetchedEmployee.getEmployee_salary() * 12;
            return new EmployeeYearlySalaryDTO(fetchedEmployee.getId(), fetchedEmployee.getEmployee_name(), fetchedEmployee.getEmployee_salary(), fetchedEmployee.getEmployee_age(), fetchedEmployee.getProfile_image(), employee_yearly_salary);
        }
        return null;
    }



}
