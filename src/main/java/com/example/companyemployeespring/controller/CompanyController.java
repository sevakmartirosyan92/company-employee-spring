package com.example.companyemployeespring.controller;
import com.example.companyemployeespring.entity.Company;
import com.example.companyemployeespring.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller
public class CompanyController {
    @Autowired
    private CompanyRepository companyRepository;
    @GetMapping("/companies")
    public String companies(ModelMap modelMap){
        List<Company> companies = companyRepository.findAll();
        modelMap.addAttribute("companies", companies);
        return "companies";
    }
    @GetMapping("/companies/add")
    public String addCompanyPage(ModelMap modelMap){
        List<Company> companies = companyRepository.findAll();
        modelMap.addAttribute("companies", companies);
        return "addCompany";
    }
    @PostMapping("/companies/add")
    public String addCompany(@ModelAttribute Company company){
        companyRepository.save(company);
        return "redirect:/companies";
    }
}
