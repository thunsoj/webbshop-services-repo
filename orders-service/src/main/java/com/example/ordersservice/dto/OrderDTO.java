package com.example.ordersservice.dto;

import com.example.ordersservice.models.Customer;
import com.example.ordersservice.models.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private Long id;
    private Date created;
    private Date updated;
    private List<Product> products;
    private Customer customer;
}

