package com.example.ordersservice.repositories;


import com.example.ordersservice.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {

}
