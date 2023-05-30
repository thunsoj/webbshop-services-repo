package com.example.ordersservice.controllers;


import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

// You can declare a response status code for a customer exception
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException {

    CustomerNotFoundException(Long id) {
        super("Could not find employee " + id);
    }

}
