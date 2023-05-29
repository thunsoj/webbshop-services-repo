package com.example.ordersservice.dto;

import com.example.ordersservice.errorhandler.ExceptionHandlers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderWithMessageDTO {
   private OrderDTO orderDto;
private ExceptionHandlers eh;
}
