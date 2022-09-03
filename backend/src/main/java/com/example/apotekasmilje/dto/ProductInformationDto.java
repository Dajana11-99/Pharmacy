package com.example.apotekasmilje.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductInformationDto {
    private Long   id;
    private String description;
    private String manufacturer;
    private String brand;
    private String dose;
    private String ingredients;
    private String characteristics;
}
