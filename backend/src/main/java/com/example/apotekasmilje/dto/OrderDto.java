package com.example.apotekasmilje.dto;

import com.example.apotekasmilje.model.enums.OrderStatus;
import com.example.apotekasmilje.model.order.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long   id;
    private String date;
    private float  totalPrice;
    private OrderStatus status;
    private Long   deliveryId;
    private Long   paymentId;
    private List<OrderProductsDto> product;
    private String userEmail;
    private int discountPercentage;
}
