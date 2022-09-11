package com.example.apotekasmilje.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BasketProductsDto {
    private ProductDto product;
    private Long basketId;
    private Integer    quantity;
}
