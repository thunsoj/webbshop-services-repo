package com.example.ordersservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Orders {

    @Id
    @GeneratedValue
    private Long id;

    private Date created;
    private Date updated;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> productIds; // Change the data type to represent the product IDs

    private Long customerId;  //Same here yao but with customer

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }
}


