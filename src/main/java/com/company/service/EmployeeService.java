package com.company.service;

import com.company.model.Employee;

import java.util.List;

public interface EmployeeService {

     List<Employee> getAllEmployees();

     Employee addEmployee(Employee employee);

     Employee updateEmployee(Employee employee);

     Employee getEmployeeById(int id);

     void deleteEmployeeById(int id);
}
