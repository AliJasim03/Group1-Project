package com.example.group1bankingapp.repository;

import com.example.group1bankingapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // This interface extends JpaRepository to provide CRUD operations.
    // JpaRepository is a JPA (Java Persistence API) specific extension of Repository.
    // It provides various methods to work with Employee persistence, such as saving, deleting, and finding Employee entities.
    // The type parameters <Employee, Long> specify the domain type as Employee and the id type as Long.
    // By extending JpaRepository, Spring Data JPA will automatically provide the implementation for EmployeeRepository interface.
    Optional<Customer> findByEmailAndPassword(String email, String password);

    Optional<Customer> findByEmail(String email);
}
