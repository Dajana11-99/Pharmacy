package com.example.apotekasmilje.mapper;

import com.example.apotekasmilje.dto.BasketProductsDto;
import com.example.apotekasmilje.model.products.BasketProducts;

import java.util.ArrayList;
import java.util.List;

public class BasketProductsMapper {

    private ProductMapper productMapper = new ProductMapper();
    public List<BasketProductsDto> basketProductsToBasketProductDtos(List<BasketProducts> basketProducts){
        List<BasketProductsDto> basketProductsDtos = new ArrayList<>();
        for(BasketProducts product:basketProducts)
            basketProductsDtos.add(new BasketProductsDto(productMapper
                    .productToProductDto(product.getProduct()),product.getBasket().getId(), product.getQuantity()));
        return basketProductsDtos;
    }
}
