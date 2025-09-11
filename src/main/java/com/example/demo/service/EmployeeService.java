package com.example.demo.service;

import com.example.demo.Employee;
import com.example.demo.dto.UpdateEmployeeReq;
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

    public Map<String, Long> create(Employee employee) {
        if (employee.getAge() < 18 || employee.getAge() > 65) {
            throw new EmployeeAgeNotLegalException();
        } else if (employee.getAge() > 30 && employee.getSalary() < 20000) {
            throw new EmployeeAgeAndSalaryNotLegalException();
        }
        employee.setStatus(true);
        employeeRepository.insert(employee);
        return Map.of("id", employee.getId());
    }

    public List<Employee> getEmployees() {
        return employeeRepository.getEmployees();
    }

    public Employee getEmployee(long id) {
        Employee employee = employeeRepository.getEmployee(id);
        if (employee == null) {
            throw new EmployeeNotFoundException("");
        }
        return employeeRepository.getEmployee(id);
    }

    public List<Employee> getEmployeeByGender(String gender) {
        return employeeRepository.filteredByGender(gender);
    }

    public List<Employee> getEmployeeByPage(int page, int size) {
        List<Employee> employees = employeeRepository.getEmployees();
        int start = (page - 1) * size;
        int end = Math.min(start + size, employees.size());
        return employees.subList(start, end);
    }

    public Employee updateEmployee(long id, Employee updateEmployee) {
        if (!updateEmployee.isStatus()) {
            throw new EmployeeNotFoundException("");
        }
        return employeeRepository.updateEmployee(id, updateEmployee);
    }

    public void deleteEmployee(long id) {
        if (!employeeRepository.getEmployee(id).isStatus()) {
            throw new EmployeeNotFoundException("");
        }
        Employee updateEmployee = employeeRepository.getEmployee(id);
        updateEmployee.setStatus(false);
        employeeRepository.updateEmployee(id, updateEmployee);
    }

    public Employee update(Long id, UpdateEmployeeReq updateEmployeeReq) {
        Employee existedEmployee = employeeRepository.getEmployee(id);
        if (existedEmployee == null) {
            throw new EmployeeNotFoundException("Employee not found with id: " + id);
        }
        if (!existedEmployee.isStatus()) {
            throw new EmployeeNotFoundException("Cannot update inactive employee");
        }
        existedEmployee.setName(updateEmployeeReq.getName());
        existedEmployee.setAge(updateEmployeeReq.getAge());
        existedEmployee.setSalary(updateEmployeeReq.getSalary());
        employeeRepository.updateEmployee(id, existedEmployee);
        return existedEmployee;
    }


    public void clear() {
        employeeRepository.clear();

    }

}
