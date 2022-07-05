package com.company.controller;


import com.company.model.Employee;
import com.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/employees")
    public String employess(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employees/list-employees";
    }

    @GetMapping("/add-employee")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees/add-employee";
    }

    @PostMapping("/save-employee")
    public String saveEmployee(@ModelAttribute("employee") @Valid Employee employee, BindingResult rs) {
        if(rs.hasErrors()){
            return "employees/add-employee";
        }

        employeeService.addEmployee(employee);
        return "redirect:/employees";

    }
    @PostMapping("/update-employee/{id}")
    public String updateEmployee(@PathVariable("id") int id, @ModelAttribute("employee")@Valid Employee employee, BindingResult rs) {
        if(rs.hasErrors()) {
            return "employees/update-employee";
        }
        employee.setId(id);
        employeeService.updateEmployee(employee);
        return "redirect:/employees";

    }

    @GetMapping("/get-employee")
    public String getEmployee(@RequestParam("employeeId") int id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        return "employees/update-employee";
    }
    @GetMapping("/delete-employee")
    public String deleteEmployee(@RequestParam("employeeId") int id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/employees";
    }
}
