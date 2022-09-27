package com.example.companyemployeespring.controller;

import com.example.companyemployeespring.entity.Company;
import com.example.companyemployeespring.entity.Employee;
import com.example.companyemployeespring.repository.CompanyRepository;
import com.example.companyemployeespring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/employees")
    public String employees(ModelMap modelMap) {
        List<Employee> employees = employeeRepository.findAll();
        modelMap.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/employees/add")
    public String addEmployeePage(ModelMap modelMap) {
        List<Company> companies = companyRepository.findAll();
        modelMap.addAttribute("companies", companies);
        return "addEmployee";
    }

    @PostMapping("/employees/add")
    public String addEmployee(@ModelAttribute Employee employee) {
        employeeRepository.save(employee);
        Company company = employee.getCompany();
        if (company != null) {
            company.setSize(company.getSize() + 1);
            companyRepository.save(company);
        }
        return "redirect:/employees";
    }

    @GetMapping("/employees/delete")
    public String delete(@RequestParam("id") int id) {
        Optional<Employee> byId = employeeRepository.findById(id);
        if (byId.isPresent()) {
            Company company = byId.get().getCompany();
            company.setSize(company.getSize() - 1);
        }
        employeeRepository.deleteById(id);
        return "redirect:/employees";
    }

}
