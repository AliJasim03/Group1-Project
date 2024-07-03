package com.example.group1bankingapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// This class represents the Employee entity in the database.
@Entity
public class Employee {

    // This is the primary key of the Employee entity.
    @Id
    // This annotation generates the ID automatically.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // These are the fields of the Employee entity.
    private String name;
    private String position;
    private double salary;

    // Default constructor required by JPA.
    public Employee() {
    }

    // Constructor to easily create an Employee object with specific values.
    public Employee(String name, String position, double salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    // Getters and setters for each field.
    // A getter allows you to retrieve the value of a field.
    // A setter allows you to set or update the value of a field.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
