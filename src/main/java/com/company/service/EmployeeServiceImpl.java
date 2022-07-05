package com.company.service;

import com.company.exception.BadRequestException;
import com.company.exception.NotFoundException;
import com.company.model.Employee;
import com.company.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService{

    private static final String DUPLICATED_CODE = "Duplicated Code ";

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee addEmployee(Employee employee){
        Employee alreadyExist = employeeRepo.findByCode(employee.getCode());
        if (ObjectUtils.isEmpty(alreadyExist)) {
            return employeeRepo.save(employee);
        } else {
            throw new BadRequestException(DUPLICATED_CODE + employee.getCode());
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Employee currentEmployee = employeeRepo.findById(employee.getId()).orElseThrow(this::getNotFoundException);
        Employee alreadyExist = employeeRepo.findByCode(employee.getCode());
        if(!ObjectUtils.isEmpty(alreadyExist) &&  !(alreadyExist.getId()==currentEmployee.getId())) {
            throw new BadRequestException(DUPLICATED_CODE + employee.getCode());
        }
        return employeeRepo.save(employee);
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeRepo.findById(id).orElseThrow(this::getNotFoundException);
    }

    @Override
    public void deleteEmployeeById(int id) {
        Employee employee = employeeRepo.findById(id).orElseThrow(this::getNotFoundException);
        employeeRepo.delete(employee);
    }

    private NotFoundException getNotFoundException() {
        return new NotFoundException("Not Found Employee With This ID ");
    }
}
