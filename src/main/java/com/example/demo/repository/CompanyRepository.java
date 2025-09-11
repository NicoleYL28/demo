package com.example.demo.repository;

import com.example.demo.Company;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CompanyRepository {

    List<Company> companies = new ArrayList<>();

    public List<Company> getCompanies();

    public void insert(Company company);

    public Company getCompany(long id);

    public Company updateCompany(long id, Company updateCompany );

    public void remove(long id);

    public void clear();
}
