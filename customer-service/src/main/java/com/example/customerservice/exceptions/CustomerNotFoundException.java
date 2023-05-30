package com.example.customerservice.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// You can declare a response status code for a customer exception
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
