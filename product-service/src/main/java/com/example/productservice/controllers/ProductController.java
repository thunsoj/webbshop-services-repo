package com.example.productservice.controllers;

import com.example.productservice.errorhandler.ErrorResponse;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository repo;

    @GetMapping("/all")
    public List<Product> findAll(){
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id){
        return repo.findById(id).orElseThrow(()-> new ProductNotFoundException(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product p){
        return new ResponseEntity<>(repo.save(p), HttpStatus.CREATED);
    }

    @PostMapping("/list")
    public ResponseEntity<List<Product>> productList(@RequestBody List<Long> ids){
        return ResponseEntity.ok(repo.findByIdIn(ids));
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ProductNotFoundException.class})
    public ErrorResponse handleProductNotFoundException(ProductNotFoundException exception){
        ErrorResponse error = new ErrorResponse();
        error.setMessage(exception.getMessage());
        error.setTimestamp(LocalDateTime.now());
        error.setStatus(HttpStatus.NOT_FOUND);
        return error;
    }

}
