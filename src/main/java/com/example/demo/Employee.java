package com.example.demo;



public class Employee {
    private int id;
    private String name;
    private int age;
    private double salary;
    private String gender;

    public Employee(String tom, int i, double v, String male) {
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
