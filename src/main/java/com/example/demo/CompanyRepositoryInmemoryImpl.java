package com.example.demo;

import com.example.demo.repository.CompanyRepository;

import java.util.ArrayList;
import java.util.List;

public class CompanyRepositoryInmemoryImpl implements CompanyRepository {
    private final List<Company> companies = new ArrayList<>();

    public List<Company> getCompanies() {
        return companies;
    }

    public void insert(Company company) {
        companies.add(company);
    }

    public Company getCompany(long id){
        for (Company company : companies) {
            if (company.getId()==(id)) {
                return company;
            }
        }
        return null;
    }

    public Company updateCompany(long id, Company updateCompany ) {
        for (Company company : companies) {
            if (company.getId() == id) {
                company.setName(updateCompany.getName());
                return company;
            }
        }
        return null;
    }

    public void remove(long id) {
        companies.removeIf(company -> company.getId() == id);
    }

    public void clear() {
        companies.clear();
    }
}
