package com.example.demo;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
public class EmployeeController {

    final List<Employee> employees = new ArrayList<>();


    @PostMapping("/employees")
    public ResponseEntity<Map<String, Object>> createEmployee(@RequestBody Employee employee) {
        int employeeId = employees.size()+1;
        employee.setId(employeeId);
        employees.add(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("id", employeeId));
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) {
        for (Employee employee : employees) {
            if (employee.getId()==(id)) {
                return ResponseEntity.status(HttpStatus.OK).body(employee).getBody();
            }
        }
        return null;
    }

    @GetMapping("/employees")
    public List<Employee> getMaleEmployee(@RequestParam String gender) {
        List<Employee> maleEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getGender().equals(gender)) {
                maleEmployees.add(employee);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(maleEmployees).getBody();
    }

    @GetMapping("/employees/all")
    public List<Employee> getAllEmployees() {
        return ResponseEntity.status(HttpStatus.OK).body(employees).getBody();
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee updateEmployee) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employee.setAge(updateEmployee.getAge());
                employee.setSalary(updateEmployee.getSalary());
                return ResponseEntity.status(HttpStatus.OK).body(employee).getBody();
            }
        }
        return null;
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employees.remove(employee);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/employees/toPage")
    public List<Employee> getEmployeesByPage(@RequestParam int page, @RequestParam int size) {
        int start = (page - 1) * size;
        int end = Math.min(start + size, employees.size());
        return ResponseEntity.status(HttpStatus.OK).body(employees.subList(start, end)).getBody();
    }


}
