package com.example.ordersservice.errorhandler;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// You can declare a response status code for a customer exception
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {


//    public CustomerNotFoundException(Long id) {
//        super("Could not find customer with id: " + id);
//    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
