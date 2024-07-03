package com.example.group1bankingapp.config;

import com.example.group1bankingapp.model.Employee;
import com.example.group1bankingapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Indicates that this class is a Spring configuration class
@Configuration
public class DBSeeder {

    // Tells Spring to automatically inject an instance of EmployeeRepository here
    // "Inject" means that Spring will create an instance of EmployeeRepository and
    // provide it to this class so we can use it without having to create it ourselves.
    @Autowired
    private EmployeeRepository employeeRepository;

    // Defines a bean that will run some code when the application starts
    @Bean
    CommandLineRunner initDatabase() {
        // This is the code that will run when the application starts
        return args -> {
            // Saves a new employee with name "John Doe", position "Developer", and salary 60000
            employeeRepository.save(new Employee("John Doe", "Developer", 60000));
            // Saves a new employee with name "Jane Smith", position "Manager", and salary 80000
            employeeRepository.save(new Employee("Jane Smith", "Manager", 80000));
        };
    }
}
