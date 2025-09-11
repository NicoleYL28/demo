package com.example.demo.exception_handler;

import com.example.demo.expection.EmployeeAgeAndSalaryNotLegalException;
import com.example.demo.expection.EmployeeAgeNotLegalException;
import com.example.demo.expection.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleEmployeeNotFoundException(Exception e){}

    @ExceptionHandler(EmployeeAgeNotLegalException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleEmployeeAgeException(Exception e){}

    @ExceptionHandler(EmployeeAgeAndSalaryNotLegalException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleEmployeeAgeAndSalaryException(Exception e){}
}
