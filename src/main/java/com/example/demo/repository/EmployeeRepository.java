package com.example.demo.repository;

import com.example.demo.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface EmployeeRepository {

    List<Employee> employees = new ArrayList<>();

    public List<Employee> getEmployees();

    public void insert(Employee employee);

    public Employee getEmployee(long id);

    public List<Employee> filteredByGender(String gender);

    public Employee updateEmployee(long id, Employee updateEmployee);

    public void clear();
}
