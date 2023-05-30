package com.example.ordersservice.controllers;

public class CustomerNotFoundException extends RuntimeException{

    CustomerNotFoundException(Long id){
        super("Customer with id " + id +" could not be found.");
    }
}


