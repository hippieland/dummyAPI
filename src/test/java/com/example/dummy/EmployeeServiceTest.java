package com.example.dummy;

import com.example.dummy.dataAccess.EmployeeAPIService;
import com.example.dummy.dto.EmployeeDTO;
import com.example.dummy.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeAPIService employeeAPIService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllEmployeesSucceeded() {
        List<EmployeeDTO> employees = new ArrayList<>();
        when(employeeAPIService.getAllEmployees()).thenReturn(employees);
        List<EmployeeDTO> retrievedEmployees = employeeService.getAllEmployees();
        assertEquals(employees, retrievedEmployees);
    }


}
