package com.example.dummy;

import com.example.dummy.dataAccess.EmployeeAPIService;
import com.example.dummy.dto.EmployeeDTO;
import com.example.dummy.dto.EmployeeYearlySalaryDTO;
import com.example.dummy.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    public void testGetEmployeeByIdSucceeded() {
        long employeeId = 1L;
        EmployeeDTO mockEmployee = new EmployeeDTO();

        when(employeeAPIService.getEmployeeById(employeeId)).thenReturn(mockEmployee);

        EmployeeDTO result = employeeService.getEmployeeById(employeeId);

        assertNotNull(result);
        assertEquals(mockEmployee, result);
    }

    @Test
    public void testGetEmployeeByIdNotFound() {
        Long employeeId = 2L;

        when(employeeService.getEmployeeById(employeeId)).thenReturn(null);

        EmployeeDTO result = employeeService.getEmployeeById(employeeId);

        assertNull(result);
    }

    @Test
    void testGetEmployeeByIdClientError() {
        long employeeId = 3L;

        when(employeeAPIService.getEmployeeById(employeeId))
                .thenThrow(new HttpClientErrorException(org.springframework.http.HttpStatus.NOT_FOUND));

        assertThrows(HttpClientErrorException.class, () -> employeeService.getEmployeeById(employeeId));
    }

    @Test
    void testGetAllEmployeesWithYearlySalary() {

        List<EmployeeDTO> mockEmployeeList = new ArrayList<>();
        mockEmployeeList.add(new EmployeeDTO(1L, "John Doe", 50000.0, 30, null));
        mockEmployeeList.add(new EmployeeDTO(2L, "Jane Doe", 60000.0, 25, null));

        when(employeeAPIService.getAllEmployees()).thenReturn(mockEmployeeList);

        List<EmployeeYearlySalaryDTO> result = employeeService.getAllEmployeesWithYearlySalary();

        assertNotNull(result);
        assertEquals(2, result.size());

        EmployeeYearlySalaryDTO firstEmployee = result.get(0);
        assertEquals(1L, firstEmployee.getId());
        assertEquals("John Doe", firstEmployee.getEmployee_name());
        assertEquals(50000.0, firstEmployee.getEmployee_salary());
        assertEquals(30, firstEmployee.getEmployee_age());
        assertNull(firstEmployee.getProfile_image());
        assertEquals(50000.0 * 12, firstEmployee.getEmployee_yearly_salary());

        EmployeeYearlySalaryDTO secondEmployee = result.get(1);
        assertEquals(2L, secondEmployee.getId());
        assertEquals("Jane Doe", secondEmployee.getEmployee_name());
        assertEquals(60000.0, secondEmployee.getEmployee_salary());
        assertEquals(25, secondEmployee.getEmployee_age());
        assertNull(secondEmployee.getProfile_image());
        assertEquals(60000.0 * 12, secondEmployee.getEmployee_yearly_salary());
    }

    @Test
    void testGetEmployeeWithYearlySalaryByIdFound() {

        Long employeeId = 1L;
        EmployeeDTO mockEmployee = new EmployeeDTO(1L, "John Doe", 50000.0, 30, null);

        when(employeeService.getEmployeeById(employeeId)).thenReturn(mockEmployee);

        EmployeeYearlySalaryDTO result = employeeService.getEmployeeWithYearlySalaryById(employeeId);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getEmployee_name());
        assertEquals(50000.0, result.getEmployee_salary());
        assertEquals(30, result.getEmployee_age());
        assertNull(result.getProfile_image());
        assertEquals(50000.0 * 12, result.getEmployee_yearly_salary());
    }

    @Test
    void testGetEmployeeWithYearlySalaryByIdNotFound() {

        Long employeeId = 2L;

        when(employeeService.getEmployeeById(employeeId)).thenReturn(null);

        EmployeeYearlySalaryDTO result = employeeService.getEmployeeWithYearlySalaryById(employeeId);

        assertNull(result);
    }

}
