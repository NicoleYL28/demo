package com.example.demo.repository;

import com.example.demo.Company;
import com.example.demo.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CompanyRepository {

    private final List<Company> companies = new ArrayList<>();

    public List<Company> getCompanies() {
        return companies;
    }

    public void insert(Company company) {
        companies.add(company);
    }

    public Company getCompany(int id){
        for (Company company : companies) {
            if (company.getId()==(id)) {
                return company;
            }
        }
        return null;
    }

    public Company updateCompany(int id, Company updateCompany ) {
        for (Company company : companies) {
            if (company.getId() == id) {
                company.setName(updateCompany.getName());
                return company;
            }
        }
        return null;
    }

    public void remove(int id) {
        companies.removeIf(company -> company.getId() == id);
    }

    public void clear() {
        companies.clear();
    }
}
