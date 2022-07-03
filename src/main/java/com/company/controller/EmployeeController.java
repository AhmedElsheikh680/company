package com.company.controller;


import com.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/emps")
    public String employess(Model model) {
        model.addAttribute("employees", employeeService.getAllEmps());
        return "employees/list-employees";
    }
}
