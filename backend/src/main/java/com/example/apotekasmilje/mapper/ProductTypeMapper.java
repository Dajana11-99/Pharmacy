package com.example.apotekasmilje.mapper;

import com.example.apotekasmilje.dto.ProductTypeDto;
import com.example.apotekasmilje.model.products.ProductType;

import java.util.ArrayList;
import java.util.List;

public class ProductTypeMapper {

    public ProductTypeDto productToProductTypeDto(ProductType productType){
        return  new ProductTypeDto(productType.getId(), productType.getName());
    }
    public ProductType productDtoToProduct(ProductTypeDto productTypeDto){
        return new ProductType(productTypeDto.getId(), productTypeDto.getName());
    }

    public List<ProductTypeDto> productTypesToProductTypeDtos(List<ProductType> productTypes){
        List<ProductTypeDto> productTypeDtos = new ArrayList<>();
        for(ProductType productType: productTypes)
            productTypeDtos.add(productToProductTypeDto(productType));
        return productTypeDtos;
    }
}
