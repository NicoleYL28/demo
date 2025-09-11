package com.example.demo;

import com.example.demo.dao.CompanyJpaRepository;
import com.example.demo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyRepositoryDBImpl implements CompanyRepository {

    @Autowired
    private CompanyJpaRepository companyJpaRepository;

    @Override
    public List<Company> getCompanies() {
        return companyJpaRepository.findAll();
    }

    @Override
    public void insert(Company company) {
        companyJpaRepository.save(company);
    }

    @Override
    public Company getCompany(long id) {
        return companyJpaRepository.findById(id).orElse(null);
    }

    @Override
    public Company updateCompany(long id, Company updateCompany) {
        return companyJpaRepository.save(updateCompany);
    }

    @Override
    public void remove(long id) {
        companyJpaRepository.deleteById(id);
    }

    @Override
    public void clear() {
        companyJpaRepository.deleteAll();
    }
}
