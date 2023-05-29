package com.example.ordersservice.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    private Long id;
    private String name;
    private int ssn;
    private Date created;
    private Date updated;

}

