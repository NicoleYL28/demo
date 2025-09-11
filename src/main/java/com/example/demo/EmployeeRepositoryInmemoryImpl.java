package com.example.demo;

import com.example.demo.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryInmemoryImpl implements EmployeeRepository {

    public List<Employee> getEmployees() {
        return employees;
    }

    public void insert(Employee employee) {
        employees.add(employee);
    }

    public Employee getEmployee(long id){
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

    public Employee updateEmployee(long id, Employee updateEmployee) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employee.setAge(updateEmployee.getAge());
                employee.setSalary(updateEmployee.getSalary());
                employee.setStatus(updateEmployee.isStatus());
                return employee;
            }
        }
        return null;
    }

    public void clear() {
        employees.clear();
    }
}
