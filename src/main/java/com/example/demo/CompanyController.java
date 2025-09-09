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

    @PutMapping("/companies/{id}")
    public Company updateCompany(@PathVariable int id, @RequestBody Company updateCompany) {
        for (Company company : companies) {
            if (company.getId() == id) {
                company.setName(updateCompany.getName());
                return ResponseEntity.status(HttpStatus.OK).body(company).getBody();
            }
        }
        return null;
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable int id) {
        for (Company company: companies) {
            if (company.getId() == id) {
                companies.remove(company);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/companies/toPage")
    public List<Company> getEmployeesByPage(@RequestParam int page, @RequestParam int size) {
        int start = (page - 1) * size;
        int end = Math.min(start + size, companies.size());
        return ResponseEntity.status(HttpStatus.OK).body(companies.subList(start, end)).getBody();
    }



}
