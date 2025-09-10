package com.example.demo;



public class Employee {
    private int id;
    private String name;
    private int age;
    private double salary;
    private String gender;
    private boolean status;

    public Employee(String name, int age, double salary, String gender) {
        this.name =name;
        this.age = age;
        this.salary = salary;
        this.gender = gender;
        this.status = true;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getGender(){
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

}
