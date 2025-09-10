package com.example.demo.service;

import com.example.demo.Employee;
import com.example.demo.expection.EmployeeAgeNotLegalException;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    private int idCounter = 0;

    public Map<String, Integer> create(Employee employee){
        if (employee.getAge() <18){
            throw new EmployeeAgeNotLegalException();
        }
        int employeeId = ++idCounter;
        employee.setId(employeeId);
        employeeRepository.insert(employee);
        return Map.of("id", employeeId);
    }

    public List<Employee> getEmployees(){
        return employeeRepository.getEmployees();
    }

    public Employee getEmployee(int id){
        return employeeRepository.getEmployee(id);
    }

    public List<Employee> getEmployeeByGender(String gender){
        return employeeRepository.filteredByGender(gender);
    }

    public List<Employee> getEmployeeByPage(int page, int size){
        List<Employee> employees = employeeRepository.getEmployees();
        int start = (page - 1) * size;
        int end = Math.min(start + size, employees.size());
        return employees.subList(start, end);
    }

    public Employee updateEmployee(int id, Employee updateEmployee){
        return employeeRepository.updateEmployee(id, updateEmployee);
    }

    public void deleteEmployee(int id){
        employeeRepository.remove(id);

    }

    public void clear(){
        employeeRepository.clear();
        idCounter = 0;
    }

}
