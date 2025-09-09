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
        int employeeId = employees.size() + 1;
        employee.setId(employeeId);
        employees.add(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("id", employeeId));
    }


}
