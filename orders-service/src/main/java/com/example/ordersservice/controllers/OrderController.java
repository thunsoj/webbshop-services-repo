package com.example.ordersservice.controllers;


import com.example.ordersservice.models.Orders;
import com.example.ordersservice.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderRepository orderRepository;
    @GetMapping("/all")
    public ResponseEntity<Iterable<Orders>> all(){
        return ResponseEntity.ok(orderRepository.findAll());
    }
}
