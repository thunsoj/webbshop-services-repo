package com.example.ordersservice.repositories;


import com.example.ordersservice.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findAllByCustomerId(Long customerId);
}
