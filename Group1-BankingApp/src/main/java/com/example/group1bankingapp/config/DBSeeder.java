package com.example.group1bankingapp.config;

import com.example.group1bankingapp.model.Customer;
import com.example.group1bankingapp.repository.CustomerRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;

// Indicates that this class is a Spring configuration class
@Configuration
public class DBSeeder {

    // Tells Spring to automatically inject an instance of EmployeeRepository here
    // "Inject" means that Spring will create an instance of EmployeeRepository and
    // provide it to this class so we can use it without having to create it ourselves.
    @Autowired
    private CustomerRepository customerRepository;

    // Defines a bean that will run some code when the application starts
    @Bean
    CommandLineRunner initDatabase() {
        // This is the code that will run when the application starts
        return args -> {
            // Saves a new employee with name "John Doe", position "Developer", and salary 60000

            // Load customers from JSON file
            List<Customer> customers = loadCustomers();

            // Save all customers to the database
            if (customers != null && !customers.isEmpty()) {
                customerRepository.saveAll(customers);
            }
        };
    }

    private List<Customer> loadCustomers() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(
                    new ClassPathResource("cust_details.json").getInputStream(),
                    new TypeReference<List<Customer>>() {
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
