package com.example.ordersservice.controllers;


import ErrorHandler.ErrorResponse;
import com.example.ordersservice.dto.OrderDTO;
import com.example.ordersservice.exceptions.CustomerNotFoundException;
import com.example.ordersservice.models.Customer;
import com.example.ordersservice.models.Orders;
import com.example.ordersservice.models.Product;
import com.example.ordersservice.repositories.OrderRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/order")
@ResponseStatus(code = HttpStatus.CREATED)
public class OrderController {


    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;


    @Value("${customer-service.url}")
    private String customerServiceBaseUrl;

    @Value("${product-service.url}")
    private String productServiceBaseUrl;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Orders>> all(){
        return ResponseEntity.ok(orderRepository.findAll());
    }

    @GetMapping("/allWithPojo")
    public ResponseEntity<?> allWithPojo() {
        return ResponseEntity.ok(orderRepository.findAll().stream().map(e-> {
            Customer customer = restTemplate.getForObject(customerServiceBaseUrl + e.getCustomerId(), Customer.class);
            List<Product> products = retrieveProducts(e.getProductIds());
            if (customer != null){
                if(products != null){
                    if(products.size() == e.getProductIds().size()){
                        return OrderDTO.builder()
                                .id(e.getId())
                                .created(e.getCreated())
                                .updated(e.getUpdated())
                                .customer(customer)
                                .products(products)
                                .build();
                    }else {
                        return "Ajajaj";
                    }
                } else {
                    return ResponseEntity.ok(new ErrorResponse().getTimestamp().getHour()).getStatusCode().getClass();
                }

            } else {
                return ResponseEntity.ok(new ErrorResponse());
            }

        }).toList());
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<?> allByUser(@PathVariable Long userId) {

        Customer customer = restTemplate.getForObject(customerServiceBaseUrl + userId, Customer.class);
        if(customer != null){
            return ResponseEntity.ok(orderRepository.findAllByCustomerId(customer.getId()).stream().map(e-> {
                List<Product> products = retrieveProducts(e.getProductIds());
                if(products != null){
                    if(products.size() == e.getProductIds().size()){
                        return OrderDTO.builder()
                                .id(e.getId())
                                .created(e.getCreated())
                                .updated(e.getUpdated())
                                .customer(customer)
                                .products(products)
                                .build();
                    }else {
                        return "Ajajaj";
                    }
                } else {
                    return ResponseEntity.ok(new ErrorResponse());
                }
            }).toList());
        } else {
            return ResponseEntity.ok(new ErrorResponse());
        }
    }

    @PostMapping("add/{customerId}")

    public ResponseEntity<?> addOrder(@PathVariable Long customerId,
                                      @RequestBody List<Long> productsIds){
        if(restTemplate.getForObject(customerServiceBaseUrl + customerId, Customer.class) != null){
            List<Product> products = retrieveProducts(productsIds);
            if (products != null){
                if(products.size() == productsIds.size()){
                    return ResponseEntity.ok(orderRepository.save(Orders.builder()
                            .customerId(customerId)
                            .productIds(productsIds)
                            .build()));
                }else{
                 throw new CustomerNotFoundException(customerId);
                }
            } else {
                return ResponseEntity.ok(new ErrorResponse());
            }
        } else {
            return ResponseEntity.ok(new ErrorResponse());

        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> byOrderId(@PathVariable Long orderId) {

        Orders order = orderRepository.findById(orderId).orElseThrow();
        Customer customer = restTemplate.getForObject(customerServiceBaseUrl + order.getCustomerId(), Customer.class);
        if (customer != null){
            List<Product> products = retrieveProducts(order.getProductIds());
            if(products != null){
                if(products.size() == order.getProductIds().size()){
                    return ResponseEntity.ok(OrderDTO.builder()
                            .id(order.getId())
                            .created(order.getCreated())
                            .updated(order.getUpdated())
                            .customer(customer)
                            .products(products)
                            .build());
                } else {
                    throw new CustomerNotFoundException(customer.getId());
                }
            }else{
                return ResponseEntity.ok(new ErrorResponse());
            }
        }else{
            return ResponseEntity.ok(new ErrorResponse());
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ CustomerNotFoundException.class })
    public ErrorResponse handleCustomerNotFoundException(CustomerNotFoundException exception) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage(exception.getMessage());
        error.setTimestamp(LocalDateTime.now());
        error.setStatus(HttpStatus.NOT_FOUND);
        return error;
    }


    private List<Product> retrieveProducts(List<Long> productIds) {
        HttpEntity<List<Long>> requestEntity = new HttpEntity<>(productIds);
        ResponseEntity<List<Product>> response = restTemplate.exchange(
                productServiceBaseUrl + "/list",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }


}
