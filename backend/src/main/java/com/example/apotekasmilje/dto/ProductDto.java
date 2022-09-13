package com.example.apotekasmilje.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long     id;
    private String   name;
    private float    price;
    private String   expirationDate;
    private int      quantity;
    private  boolean onSale;
    private List<ImageDto> image;
    private ProductInformationDto productInformation;
    private Long idCategory;
    private  List<CharacteristicsDto> characteristics;
    private int discount;

}
