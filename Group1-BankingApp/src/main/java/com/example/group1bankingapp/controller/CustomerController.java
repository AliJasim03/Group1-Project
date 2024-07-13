package com.example.group1bankingapp.controller;

import com.example.group1bankingapp.model.Customer;
import com.example.group1bankingapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

// This class handles HTTP requests for Employee operations.
@RestController
// Maps HTTP requests to specific handler methods in this controller. The base path for all endpoints in this controller is "/api/employees".
@RequestMapping("/api")
public class CustomerController {

    // Injecting the EmployeeRepository to use its methods.
    // "Injecting" means that Spring will create an instance of EmployeeRepository and
    // provide it to this class so we can use it without having to create it ourselves.
    @Autowired
    private CustomerRepository customerRepository;

    // Endpoint to get all employees.
    // This method will be called when a GET request is made to "/api/employees".
    @GetMapping("/getAllCustomers")
    public List<Customer> getAllCustomers() {
        // Returns a list of all employees from the database.
        return customerRepository.findAll();
    }

    // Endpoint to get a single employee by ID.
    // This method will be called when a GET request is made to "/api/employees/{id}".
    @PostMapping("/customerByEmail")
    public ResponseEntity<Customer> getCustomerByEmail(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        return ResponseEntity.ok(customer);
    }

    // Endpoint to get a single customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        return ResponseEntity.ok(customer);
    }
}
