package com.company.service;

import com.company.model.Employee;
import com.company.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public List<Employee> getAllEmps() {
        return employeeRepo.findAll();
    }

    public Employee addEmployee(Employee employee) throws Exception {
        Employee alreadyExist = this.employeeRepo.findByCode(employee.getCode());
        if (null == alreadyExist) {
            return employeeRepo.save(employee);
        } else {
            throw new Exception("Duplicated Code "+ employee.getCode());
        }
    }
    public Employee updateEmployee(Employee employee) throws Exception {
        Employee currentEmployee = this.employeeRepo.findById(employee.getId()).get();
        if(currentEmployee.getCode().equals(employee.getCode())) {
            return employeeRepo.save(employee);

        }
        Employee alreadyExist = this.employeeRepo.findByCode(employee.getCode());
        if (null == alreadyExist) {
            return employeeRepo.save(employee);
        } else {
            throw new Exception("Duplicated Code " + employee.getCode());
        }
    }
    public Employee commit(Employee employee) throws Exception {
        if(employee.getId() ==0){
            return addEmployee(employee);
        } else {
            return updateEmployee(employee);
        }
    }

    public Employee getEmployee(int id) {
        return employeeRepo.findById(id).get();
    }
    public void deleteEmployee(int id) {
        employeeRepo.deleteById(id);
    }
}
