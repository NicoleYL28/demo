package com.example.demo.dto;

public class UpdateEmployeeReq {

    private final String name;
    private final int age;
    private final double salary;

    public UpdateEmployeeReq(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }


    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public double getSalary() {
        return this.salary;
    }
}
