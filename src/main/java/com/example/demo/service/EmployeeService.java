package com.example.demo.service;

import com.example.demo.Employee;
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


    public List<Employee> employees = new ArrayList<>();

    public Map<String, Integer> create(Employee employee){
        int employeeId = employees.size()+1;
        employee.setId(employeeId);
        employees.add(employee);
        return Map.of("id", employeeId);
    }

    public Employee getEmployee(int id){
        for (Employee employee : employees) {
            if (employee.getId()==(id)) {
                return employee;
            }
        }
        return null;
    }

    public List<Employee> getEmployeeByGender(String gender){

        List<Employee> filtered = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getGender().equals(gender)) {
                filtered.add(employee);
            }
        }
        return filtered;
    }

    public List<Employee> getEmployeeByPage(int page, int size){
        int start = (page - 1) * size;
        int end = Math.min(start + size, employees.size());
        return employees.subList(start, end);
    }

    public Employee updateEmployee(int id, Employee updateEmployee){
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employee.setAge(updateEmployee.getAge());
                employee.setSalary(updateEmployee.getSalary());
                return employee;
            }
        }
        return null;
    }

    public void deleteEmployee(int id){
        employees.removeIf(employee -> employee.getId() == id);
    }



    public List<Employee> getEmployees(){
        return this.employees;
    }

}
