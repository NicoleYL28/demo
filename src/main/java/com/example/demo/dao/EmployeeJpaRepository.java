package com.example.demo.dao;

import com.example.demo.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeJpaRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByGender(String gender);
}
