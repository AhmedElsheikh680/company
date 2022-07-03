package com.company.controller;


import com.company.model.Employee;
import com.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/emps")
    public String employess(Model model) {
        model.addAttribute("employees", employeeService.getAllEmps());
        return "employees/list-employees";
    }

    @GetMapping("/add-employee")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees/add-employee";
    }

    @PostMapping("/save-employee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) throws Exception {

        employeeService.commit(employee);
        return "redirect:/emps";

    }

    @GetMapping("/get-employee")
    public String getEmployee(@RequestParam("employeeId") int id, Model model) {
        model.addAttribute("employee", employeeService.getEmployee(id));
        return "employees/add-employee";
    }
    @GetMapping("/delete-employee")
    public String deleteEmployee(@RequestParam("employeeId") int id) {
        employeeService.deleteEmployee(id);
        return "redirect:/emps";
    }

}
