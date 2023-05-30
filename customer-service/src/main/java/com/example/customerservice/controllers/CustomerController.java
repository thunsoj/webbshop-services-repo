package com.example.customerservice.controllers;


import com.example.customerservice.errorhandler.ErrorResponse;
import com.example.customerservice.exceptions.CustomerNotFoundException;
import com.example.customerservice.models.Customer;
import com.example.customerservice.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        var customer =  customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Cant find customer with id: " + id));
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    //lägg till ny kund
    @PostMapping("/add")
    public ResponseEntity<?> addCustomer(@RequestBody Customer c) {
        if(!customerRepository.existsBySsn(c.getSsn())){
            return ResponseEntity.ok(customerRepository.save(c));
        } else {
            return ResponseEntity.ok("User already exist");
        }
    }

    //ta bort en kund baserat på id
    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
        return "The customer with ID " + id + " was deleted.";
    }

    //@ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler({ CustomerNotFoundException.class })
//    public ErrorResponse handleCustomerNotFoundException(CustomerNotFoundException exception) {
//        ErrorResponse error = new ErrorResponse();
//        error.setMessage(exception.getMessage());
//        error.setTimestamp(LocalDateTime.now());
//        error.setStatus(HttpStatus.NOT_FOUND);
//        return error;
//    }
}
