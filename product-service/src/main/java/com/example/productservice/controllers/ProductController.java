package com.example.productservice.controllers;

import com.example.productservice.models.Product;
import com.example.productservice.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository repo;

    @RequestMapping("/all")
    public List<Product> findAll(){
        return repo.findAll();
    }

    @RequestMapping("/{id}")
    public Product findById(@PathVariable Long id){
        return repo.findById(id).get();
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product p){
        return new ResponseEntity<>(repo.save(p), HttpStatus.CREATED);
    }

    @PostMapping("/list")
    public ResponseEntity<List<Product>> productList(@RequestBody List<Long> ids){
        return ResponseEntity.ok(repo.findByIdIn(ids));
    }
}
