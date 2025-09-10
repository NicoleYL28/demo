package com.example.demo.service;

import com.example.demo.Employee;
import com.example.demo.expection.EmployeeAgeAndSalaryNotLegalException;
import com.example.demo.expection.EmployeeAgeNotLegalException;
import com.example.demo.expection.EmployeeNotFoundException;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    private int idCounter = 0;

    public Map<String, Integer> create(Employee employee) {
        if (employee.getAge() <18 || employee.getAge() > 65){
            throw new EmployeeAgeNotLegalException();
        } else if (employee.getAge() > 30 && employee.getSalary() < 20000) {
            throw new EmployeeAgeAndSalaryNotLegalException();
        }
        int employeeId = ++idCounter;

        employee.setId(employeeId);
        employeeRepository.insert(employee);
        return Map.of("id", employeeId);
    }

    public List<Employee> getEmployees(){
        return employeeRepository.getEmployees();
    }

    public Employee getEmployee(int id) {
        Employee employee = employeeRepository.getEmployee(id);
        if(employee == null){
            throw new EmployeeNotFoundException();
        }
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
