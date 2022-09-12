package com.example.apotekasmilje.dto;

import com.example.apotekasmilje.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderInformationDto {
    private Long   id;
    private String date;
    private float  totalPrice;
    private OrderStatus status;
    private PaymentDto payment;
    private DeliveryDto   delivery;
    private List<OrderProductsDto> product;
    private String fullName;
    private String phoneNum;
    private String address;
    private String zipCode;
    private String place;
    private int discountPercentage;
}
