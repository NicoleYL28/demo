package com.example.demo.service;

import com.example.demo.Company;
import com.example.demo.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;


    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Map<String, Long> create(Company company){
        companyRepository.insert(company);
        return Map.of("id", company.getId());
    }

    public List<Company> getCompanies(){
        return companyRepository.getCompanies();
    }

    public Company getCompanyById(long id){
        return companyRepository.getCompany(id);
    }

    public List<Company> getCompanyByPage(int page, int size){
        List<Company> companies = companyRepository.getCompanies();
        int start = (page - 1) * size;
        int end = Math.min(start + size, companies.size());
        return companies.subList(start, end);
    }

    public Company updateCompany(long id, Company updateCompany){
        return  companyRepository.updateCompany(id, updateCompany);
    }

    public void deleteCompany(long id){
        companyRepository.remove(id);
    }

    public void clear(){
        companyRepository.clear();
    }


}

