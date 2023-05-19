package com.example.customerservice.controllers;


import com.example.customerservice.models.Customer;
import com.example.customerservice.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")

    public class CustomerController {

    private final CustomerRepository customerRepository;

    //visa alla kunder
    @GetMapping("/all")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    //hitta kund baserat på id
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id).get();
    }

    //lägg till ny kund
    @PostMapping("/add")
    public String addCustomer(@RequestBody Customer c) {
        customerRepository.save(c);
        return "New customer " + c.getName() + " was added";
    }

    //ta bort en kund baserat på id
    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
        return "The customer with ID " + id + " was deleted.";
    }
}
