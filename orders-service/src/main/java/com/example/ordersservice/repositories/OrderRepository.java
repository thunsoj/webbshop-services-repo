package com.example.ordersservice.repositories;


import com.example.ordersservice.models.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Orders, Long> {

}
