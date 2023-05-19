package com.example.customerservice.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int ssn;

    private Date created;
    private Date updated;


    @PrePersist
    protected void onCreate() {
        created = new Date();
    }
    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

}

