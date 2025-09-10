package com.example.demo.controller;


import com.example.demo.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employees")
    public ResponseEntity<Map<String, Integer>> createEmployee(@RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.create(employee));
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployee(id));

    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployee(@RequestParam(required = false) String gender,
                                      @RequestParam(required = false) Integer page,
                                      @RequestParam(required = false) Integer size) {
        if(gender != null){
            List<Employee> filtered = employeeService.getEmployeeByGender(gender);
            return ResponseEntity.status(HttpStatus.OK).body(filtered);
        }

        if(page != null && size != null){
            List<Employee> filtered = employeeService.getEmployeeByPage(page, size);
            return ResponseEntity.status(HttpStatus.OK).body(filtered);
        }

        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployees());
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee updateEmployee) {
        Employee employee = employeeService.updateEmployee(id, updateEmployee);
        return ResponseEntity.status(HttpStatus.OK).body(employee).getBody();
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
