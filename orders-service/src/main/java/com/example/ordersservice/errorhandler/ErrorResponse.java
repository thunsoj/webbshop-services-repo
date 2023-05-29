package com.example.ordersservice.errorhandler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ErrorResponse {
    private int status;
    private String error;
    private String message;

}