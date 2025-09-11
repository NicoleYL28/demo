package com.example.demo;

import com.example.demo.dao.EmployeeJpaRepository;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepositoryDBImpl implements EmployeeRepository {

    @Autowired
    private EmployeeJpaRepository employeeJpaRepository;

    @Override
    public List<Employee> getEmployees() {
        return employeeJpaRepository.findAll();
    }

    @Override
    public void insert(Employee employee) {
        employeeJpaRepository.save(employee);
    }

    @Override
    public Employee getEmployee(long id) {
        return employeeJpaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Employee> filteredByGender(String gender) {
        return employeeJpaRepository.findByGender(gender);
    }

    @Override
    public Employee updateEmployee(long id, Employee updateEmployee) {
        return employeeJpaRepository.save(updateEmployee);
    }

    @Override
    public void clear() {
        employeeJpaRepository.deleteAll();
    }
}
