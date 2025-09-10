package com.example.demo.service;

import com.example.demo.Company;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CompanyService {

    public List<Company> companies = new ArrayList<>();

    public Map<String, Integer> create(Company company){
        int companyId = companies.size()+1;
        company.setId(companyId);
        companies.add(company);
        return Map.of("id", companyId);
    }

    public Company getCompanyById(int id){
        for (Company company : companies) {
            if (company.getId()==(id)) {
                return company;
            }
        }
        return null;
    }


    public List<Company> getCompanyByPage(int page, int size){
        int start = (page - 1) * size;
        int end = Math.min(start + size, companies.size());
        return companies.subList(start, end);
    }

    public Company updateCompany(int id, Company updateCompany){
        for (Company company : companies) {
            if (company.getId() == id) {
                company.setName(updateCompany.getName());
                return company;
            }
        }
        return null;
    }

    public void deleteCompany(int id){
        companies.removeIf(company -> company.getId() == id);
    }



    public List<Company> getEmployees(){
        return this.companies;
    }

}

