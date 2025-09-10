package com.example.demo.repository;

import com.example.demo.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {

    private final List<Employee> employees = new ArrayList<>();

    public List<Employee> getEmployees() {
        return employees;
    }

    public void insert(Employee employee) {
        employees.add(employee);
    }

    public Employee getEmployee(int id){
        for (Employee employee : employees) {
            if (employee.getId()==(id)) {
                return employee;
            }
        }
        return null;
    }

    public List<Employee> filteredByGender(String gender) {
        List<Employee> filtered = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getGender().equals(gender)) {
                filtered.add(employee);
            }
        }
        return filtered;
    }

    public Employee updateEmployee(int id, Employee updateEmployee) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employee.setAge(updateEmployee.getAge());
                employee.setSalary(updateEmployee.getSalary());
                return employee;
            }
        }
        return null;
    }

    public void remove(int id) {
        for(Employee employee: employees){
            if(employee.getId() == id){
                employee.setStatus(false);
            }
        }
    }

    public void clear() {
        employees.clear();
    }
}
