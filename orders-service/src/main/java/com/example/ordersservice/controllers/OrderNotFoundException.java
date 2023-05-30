package com.example.ordersservice.controllers;

public class OrderNotFoundException extends RuntimeException{

    OrderNotFoundException(Long id){
        super("Order with id " + id +" could not be found.");
    }
}


