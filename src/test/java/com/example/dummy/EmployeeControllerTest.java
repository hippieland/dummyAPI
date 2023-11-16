package com.example.dummy;

import com.example.dummy.controller.EmployeeController;
import com.example.dummy.dto.EmployeeDTO;
import com.example.dummy.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() { MockitoAnnotations.openMocks(this);}

    @Test
    public void testGetAllEmployeesSucceeded() {
        List<EmployeeDTO> employees = new ArrayList<>();
        when(employeeService.getAllEmployees()).thenReturn(employees);

        employees = employeeController.getAllEmployees();
        ResponseEntity<List<EmployeeDTO>> response = new ResponseEntity<>(employees, HttpStatus.OK);

        verify(employeeService, times(1)).getAllEmployees();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employees, response.getBody());
    }
}
