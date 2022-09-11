package com.example.apotekasmilje.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BasketDto {
    private Long   id;
    private float  totalPrice;
    private String userEmail;
    private List<BasketProductsDto> basketProduct;

}
