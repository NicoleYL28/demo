package com.example.demo;


import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int age;
    private double salary;
    private String gender;
    private boolean status;

    @Column(name = "company_id")
    private long companyId;

    public Employee(String name, int age, double salary, String gender) {
        this.name =name;
        this.age = age;
        this.salary = salary;
        this.gender = gender;
        this.status = true;
    }

    public Employee() {}

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

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long id){
        this.companyId = id;
    }

}
