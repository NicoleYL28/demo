package com.example.demo.controller;

import com.example.demo.Company;
import com.example.demo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping("/companies")
    public ResponseEntity<Map<String, Integer>> createCompany(@RequestBody Company company) {
        Map<String, Integer> result = companyService.create(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/companies")
    public ResponseEntity<List<Company>> getCompanies(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        if (page!=null && size != null){
            List<Company> filtered = companyService.getCompanyByPage(page, size);
            return ResponseEntity.status(HttpStatus.OK).body(filtered);
        }
        return ResponseEntity.status(HttpStatus.OK).body(companyService.getCompanies());
    }



    @GetMapping("/companies/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable int id) {
        Company company = companyService.getCompanyById(id);
        return ResponseEntity.status(HttpStatus.OK).body(company);
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable int id, @RequestBody Company updateCompany) {

        Company company = companyService.updateCompany(id, updateCompany);
        return ResponseEntity.status(HttpStatus.OK).body(company);
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable int id) {
        companyService.deleteCompany(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
