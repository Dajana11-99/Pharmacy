package com.example.apotekasmilje.mapper;

import com.example.apotekasmilje.dto.ProductInformationDto;
import com.example.apotekasmilje.model.products.ProductInformation;

public class ProductInformationMapper {

    public ProductInformation productInformationDtoToProductInformation(ProductInformationDto productInformationDto){
        return  new ProductInformation(productInformationDto.getId(), productInformationDto.getDescription(),
                productInformationDto.getManufacturer(), productInformationDto.getBrand(), productInformationDto.getDose(),
                productInformationDto.getIngredients(), productInformationDto.getCharacteristics());
    }
    public ProductInformationDto productInformationToProductInformationDto(ProductInformation productInformation){
        return new ProductInformationDto(productInformation.getId(), productInformation.getDescription(), productInformation.getManufacturer(),
                productInformation.getBrand(), productInformation.getDose(), productInformation.getIngredients(), productInformation.getCharacteristics());
    }
}
