package com.example.apotekasmilje.mapper;

import com.example.apotekasmilje.dto.OrderProductsDto;
import com.example.apotekasmilje.model.order.OrderProducts;

import java.util.ArrayList;
import java.util.List;

public class ProductOrderMapper {
    private ProductMapper productMapper = new ProductMapper();
    public List<OrderProductsDto> orderProductsToOrderProductDtos(List<OrderProducts> orderProducts){
        List<OrderProductsDto>orderProductsDtos = new ArrayList<>();
        for(OrderProducts products: orderProducts)
            orderProductsDtos.add(new OrderProductsDto(productMapper.productToProductDto(products.getProduct()),products.getQuantity()));
   return orderProductsDtos;
    }
}
