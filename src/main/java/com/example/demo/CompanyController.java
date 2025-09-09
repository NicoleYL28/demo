package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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



}
