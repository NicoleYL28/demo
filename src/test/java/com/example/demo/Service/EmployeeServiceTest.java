package com.example.demo.repository;

import com.example.demo.Employee;
import com.example.demo.expection.EmployeeAgeAndSalaryNotLegalException;
import com.example.demo.expection.EmployeeAgeNotLegalException;
import com.example.demo.expection.EmployeeNotFoundException;
import com.example.demo.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class EmployeeServiceTest {
    @InjectMocks
    EmployeeService employeeService;

    @Captor
    private ArgumentCaptor<Employee> employeeArgumentCaptor;

    @Mock
    EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        employeeService.clear();
    }

    @Test
    void should_not_create_employee_when_when_post_given_age_below_18(){
        Employee employee = new Employee("Tom", 15, 100.0, "Male");
        assertThrows(EmployeeAgeNotLegalException.class, () ->{
            employeeService.create(employee);
        });
        verify(employeeRepository,never()).insert(any());
    }

    @Test
    void should_not_create_employee_when_when_post_given_age_excess_65(){
        Employee employee = new Employee("Tom", 66, 1000.0, "Male");
        assertThrows(EmployeeAgeNotLegalException.class, () ->{
            employeeService.create(employee);
        });
        verify(employeeRepository,never()).insert(any());
    }

    @Test
    void should_return_null_when_employee_is_not_found(){

        assertThrows(EmployeeNotFoundException.class, () ->{
            employeeService.getEmployee(1);
        });
        verify(employeeRepository,times(1)).getEmployee(1);
    }

    @Test
    void should_return_error_when_create_given_employee_name_excess_30_and_salary_below_20000(){

        Employee employee = new Employee("Tom", 35, 15000.0, "Male");
        assertThrows(EmployeeAgeAndSalaryNotLegalException.class, () ->{
            employeeService.create(employee);
        });
        verify(employeeRepository,never()).insert(any());
    }

    @Test
    void should_default_employee_status_true_when_create_given_valid_body(){
        Employee employee = new Employee("Tom", 20, 800.0, "Male");
        employeeService.create(employee);
        verify(employeeRepository,times(1)).insert(employeeArgumentCaptor.capture());
        Employee value = employeeArgumentCaptor.getValue();
        assertTrue(value.isStatus());
    }

    @Test
    void should_set_employee_status_false_when_delete_given_valid_body(){
        Employee employee = new Employee("Tom", 20, 800.0, "Male");
        employeeService.create(employee);
        when(employeeRepository.getEmployee(1)).thenReturn(employee);
        employeeService.deleteEmployee(1);
        employee.setStatus(false);
        verify(employeeRepository,times(1)).remove(1);
        assertFalse(employee.isStatus());


    }

    @Test
    void should_return_error_when_update_given_left_employee(){
        Employee employee = new Employee("Tom", 20, 800.0, "Male");
        employee.setStatus(false);

        Employee updateEmployee = new Employee("Tom", 21, 1000.0, "Male");
        updateEmployee.setStatus(false);
        assertThrows(EmployeeNotFoundException.class, () ->{
            employeeService.updateEmployee(1, updateEmployee);
        });
    }

}