package com.example.ordersservice.controllers;

public class ProductNotFoundException extends RuntimeException {

    ProductNotFoundException(){
        super("Product with id " + id +" could not be found.");
    }
}
