package com.example.ordersservice.controllers;



import com.example.ordersservice.dto.OrderDTO;
import com.example.ordersservice.errorhandler.RestTemplateResponseErrorHandler;
import com.example.ordersservice.exceptions.OrderNotFoundException;
import com.example.ordersservice.exceptions.ResourceNotFoundException;
import com.example.ordersservice.models.Customer;
import com.example.ordersservice.models.Orders;
import com.example.ordersservice.models.Product;
import com.example.ordersservice.repositories.OrderRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.lang.module.ResolutionException;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/order")
@ResponseStatus(code = HttpStatus.CREATED)
public class OrderController {


    private OrderRepository orderRepository;
    private RestTemplate restTemplate;


    @Value("${customer-service.url}")
    private String customerServiceBaseUrl;

    @Value("${product-service.url}")
    private String productServiceBaseUrl;


    @Autowired
    public OrderController(OrderRepository orderRepository, RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        RestTemplateResponseErrorHandler errorHandler = new RestTemplateResponseErrorHandler();
        restTemplate.setErrorHandler(errorHandler);
        this.restTemplate = restTemplate;
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Orders>> all(){
        return ResponseEntity.ok(orderRepository.findAll());
    }

    @GetMapping("/allWithPojo")
    public ResponseEntity<List<OrderDTO>> allWithPojo() {
        return ResponseEntity.ok(orderRepository.findAll().stream().map(e-> {
            Customer customer = restTemplate.getForObject(customerServiceBaseUrl + e.getCustomerId(), Customer.class);
            List<Product> products = retrieveProducts(e.getProductIds());
                return OrderDTO.builder()
                        .id(e.getId())
                        .created(e.getCreated())
                        .updated(e.getUpdated())
                        .customer(customer)
                        .products(products)
                        .build();
        }).toList());
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<OrderDTO>> allByUser(@PathVariable Long userId) {

        Customer customer = restTemplate.getForObject(customerServiceBaseUrl + userId, Customer.class);

        List<Orders> allOrdersOnCustomer = orderRepository.findAllByCustomerId(customer.getId());

        List<OrderDTO> orderDTOs = allOrdersOnCustomer.stream().map(e-> {
            List<Product> products = retrieveProducts(e.getProductIds());
            return OrderDTO.builder()
                    .id(e.getId())
                    .created(e.getCreated())
                    .updated(e.getUpdated())
                    .customer(customer)
                    .products(products)
                    .build();
        }).toList();

        return new ResponseEntity<>(orderDTOs, HttpStatus.OK);
    }

    @PostMapping("add/{customerId}")
    public ResponseEntity<?> addOrder(@PathVariable Long customerId, @RequestBody List<Long> productsIds){
        restTemplate.getForObject(customerServiceBaseUrl + customerId, Customer.class);
        List<Product> products = retrieveProducts(productsIds);
        if(products.size() != productsIds.size()){
            throw new ResourceNotFoundException("Error, tried to fetch product that doesn't exist");
        }
        return new ResponseEntity<>(orderRepository.save(Orders.builder()
                .customerId(customerId)
                .productIds(products.stream().map(Product::getId).toList())
                .build()), HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> byOrderId(@PathVariable Long orderId) {

        Orders order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Could not find order with ID: " + orderId));
        Customer customer = restTemplate.getForObject(customerServiceBaseUrl + order.getCustomerId(), Customer.class);
        List<Product> products = retrieveProducts(order.getProductIds());
        return ResponseEntity.ok(OrderDTO.builder()
                .id(order.getId())
                .created(order.getCreated())
                .updated(order.getUpdated())
                .customer(customer)
                .products(products)
                .build());

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