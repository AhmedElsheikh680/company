package com.company.controller;
import com.company.model.Employee;
import com.company.service.EmployeeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeControllerTests {

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;

    Employee employee = new Employee();

    @BeforeEach
    public void setUp() {
        employee = new Employee(1, "testName", "testCode", "testAddress");
    }

    @Test
    void whenGetAllEmployees_ThenReturnSuccess() throws Exception {

        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(employee));

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("employees", hasSize(1)))
                .andExpect(model().attribute("employees", hasItem(
                        allOf(
                                hasProperty("id", is(1)),
                                hasProperty("name", is("testName")

                                )
                        ))));

        verify(employeeService, times(1)).getAllEmployees();
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    void whenGetEmployee_ThenReturnSuccess() throws Exception {

        when(employeeService.getEmployeeById(ArgumentMatchers.anyInt())).thenReturn(employee);

        mockMvc.perform(get("/get-employee").param("employeeId", "1"))
                .andDo(result -> {
                    Assertions.assertThat(result.getResponse().getStatus())
                            .isEqualTo(HttpStatus.OK.value());

                    Assertions.assertThat(result.getResponse()).isNotNull();
                });
    }
    @Test
    void whenAddEmployee_ThenReturnSuccess() throws Exception {

        when(employeeService.getEmployeeById(ArgumentMatchers.anyInt())).thenReturn(employee);

        mockMvc.perform(get("/add-employee").param("employee", String.valueOf(employee)))
                .andDo(result -> {
                    Assertions.assertThat(result.getResponse().getStatus())
                            .isEqualTo(HttpStatus.OK.value());

                    Assertions.assertThat(result.getResponse()).isNotNull();
                });
    }

    @Test
    void whenDeleteEmployee_ThenReturnSuccess() throws Exception {

        doNothing().when(employeeService).deleteEmployeeById(ArgumentMatchers.anyInt());

        mockMvc.perform(get("/delete-employee").param("employeeId", "1"))
                .andDo(result -> {
                    Assertions.assertThat(result.getResponse().getStatus())
                            .isEqualTo(HttpStatus.FOUND.value());

                    Assertions.assertThat(result.getResponse()).isNotNull();
                });
    }

    @Test
    void whenGetEmployee_InvalidUri_ThenThrowException() throws Exception {

        Employee employee = new Employee(1, "testName", "testCode", "testAddress");

        when(employeeService.getEmployeeById(ArgumentMatchers.anyInt())).thenReturn(employee);

        mockMvc.perform(get("/invalid-uri").param("employeeId", "1"))
                .andDo(result -> {
                    Assertions.assertThat(result.getResponse().getStatus())
                            .isEqualTo(HttpStatus.NOT_FOUND.value());

                    Assertions.assertThat(result.getResponse()).isNotNull();
                });
    }

}
