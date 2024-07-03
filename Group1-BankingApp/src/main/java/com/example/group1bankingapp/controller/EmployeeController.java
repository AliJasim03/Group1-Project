package com.example.group1bankingapp.controller;

import com.example.group1bankingapp.model.Employee;
import com.example.group1bankingapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

// This class handles HTTP requests for Employee operations.
@RestController
// Maps HTTP requests to specific handler methods in this controller. The base path for all endpoints in this controller is "/api/employees".
@RequestMapping("/api/employees")
public class EmployeeController {

    // Injecting the EmployeeRepository to use its methods.
    // "Injecting" means that Spring will create an instance of EmployeeRepository and
    // provide it to this class so we can use it without having to create it ourselves.
    @Autowired
    private EmployeeRepository employeeRepository;

    // Endpoint to get all employees.
    // This method will be called when a GET request is made to "/api/employees".
    @GetMapping
    public List<Employee> getAllEmployees() {
        // Returns a list of all employees from the database.
        return employeeRepository.findAll();
    }

    // Endpoint to get a single employee by ID.
    // This method will be called when a GET request is made to "/api/employees/{id}".
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        // Attempt to find the employee by ID.
        // If not found, throw a ResponseStatusException with BAD_REQUEST status.
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee not found"));
    }
}
