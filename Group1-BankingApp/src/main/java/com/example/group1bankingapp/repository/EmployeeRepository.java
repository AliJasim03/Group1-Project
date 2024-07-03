package com.example.group1bankingapp.repository;

import com.example.group1bankingapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

// This interface handles database operations for Employee entities.
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // This interface extends JpaRepository to provide CRUD operations.
    // JpaRepository is a JPA (Java Persistence API) specific extension of Repository.
    // It provides various methods to work with Employee persistence, such as saving, deleting, and finding Employee entities.
    // The type parameters <Employee, Long> specify the domain type as Employee and the id type as Long.
    // By extending JpaRepository, Spring Data JPA will automatically provide the implementation for EmployeeRepository interface.
}
