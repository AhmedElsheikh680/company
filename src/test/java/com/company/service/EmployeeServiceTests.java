package com.company.service;

import com.company.model.Employee;
import com.company.repo.EmployeeRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTests {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepo employeeRepo;

    Employee employee = new Employee();

    @BeforeEach
    public void setUp() {
        employee = new Employee(1, "testName", "testCode", "testAddress");
    }


    @Test
    void whenGetEmployee_ThenReturnSuccess() {
        when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));
        Employee user = employeeService.getEmployeeById(1);
        assertEquals(1, user.getId());

    }
    @Test
    void whenDeleteEmployee_ThenReturnSuccess() {
        when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));
        assertDoesNotThrow(
                () -> employeeService.deleteEmployeeById(1));
    }
    @Test
    void whenGetAllEmployee_ThenReturnSuccess() {
        assertDoesNotThrow(
                () -> employeeService.getAllEmployees());
    }

    @Test
    void whenUpdateEmployee_ThenReturnSuccess() {
        when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));
        Employee employee = new Employee(1, "testName", "testCode", "testAddress");
        assertDoesNotThrow(
                () -> employeeService.updateEmployee(employee));
    }

    @Test 
    void whenAddEmployee_ThenReturnSuccess() {
        assertDoesNotThrow(
                () -> employeeService.addEmployee(employee));
    }
}
