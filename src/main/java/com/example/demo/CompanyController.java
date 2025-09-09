package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class CompanyController {

    final List<Company> companies = new ArrayList<>();

    @PostMapping("/companies")
    public ResponseEntity<Map<String, Object>> createCompany(@RequestBody Company company) {
        int companyId = companies.size() + 1;
        company.setId(companyId);
        companies.add(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("id", companyId));
    }

    @GetMapping("/companies/all")
    public List<Company> getAllCompanies() {
        return ResponseEntity.status(HttpStatus.OK).body(companies).getBody();
    }

    @GetMapping("/companies/{id}")
    public Company getCompany(@PathVariable int id) {
        for (Company company : companies) {
            if (company.getId()==(id)) {
                return ResponseEntity.status(HttpStatus.OK).body(company).getBody();
            }
        }
        return null;
    }





}
