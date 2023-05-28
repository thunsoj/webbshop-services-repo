package com.example.ordersservice.controllers;


import com.example.ordersservice.dto.OrderDTO;
import com.example.ordersservice.models.Customer;
import com.example.ordersservice.models.Orders;
import com.example.ordersservice.models.Product;
import com.example.ordersservice.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderRepository orderRepository;
    private RestTemplate restTemplate;


    @Value("${customer-service.url}")
    private String customerServiceBaseUrl;

    @Value("${product-service.url}")
    private String productServiceBaseUrl;

    @Autowired
    public OrderController(OrderRepository orderRepository, RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Orders>> all(){
        return ResponseEntity.ok(orderRepository.findAll());
    }

    @GetMapping("/test")
    public ResponseEntity<Customer> test() {
        Customer customer = restTemplate.getForObject("http://webbshop-services-repo-customer-service-1:8080/customer/1", Customer.class);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(customer);
    }

    @GetMapping(path = "/test2")
    public @ResponseBody Customer getCustomer() {
        return restTemplate.getForObject("http://webbshop-services-repo-customer-service-1:8080/customer/1", Customer.class);
    }

    @GetMapping("/allWithPojo")
    public ResponseEntity<List<OrderDTO>> allWithPojo() {
        List<OrderDTO> ordersToSend = new ArrayList<>();
        List<Orders> orders = orderRepository.findAll();

        for (Orders order : orders) {
            Customer customer = restTemplate.getForObject(customerServiceBaseUrl + order.getCustomerId(), Customer.class);

            if (customer != null) {
                List<Product> products = new ArrayList<>();
                for (Long productId : order.getProductIds()) {
                    Product product = restTemplate.getForObject(productServiceBaseUrl + productId, Product.class);
                    if (product != null) {
                        products.add(product);
                    }
                }

                OrderDTO orderDTO = new OrderDTO(order.getId(), order.getCreated(), order.getUpdated(), products, customer);
                ordersToSend.add(orderDTO);
            }
        }

        return ResponseEntity.ok(ordersToSend);
    }

}
