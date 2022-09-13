package com.example.apotekasmilje.mapper;


import com.example.apotekasmilje.dto.ProductSaleDto;
import com.example.apotekasmilje.model.products.ProductSale;

import java.util.ArrayList;
import java.util.List;

public class SaleProductsMapper {

    private ProductMapper productMapper = new ProductMapper();

    List<ProductSaleDto> productSaleToProductSaleDtos(List<ProductSale> saleProducts){
        List<ProductSaleDto> productSaleDtos = new ArrayList<>();
        for(ProductSale productSale: saleProducts)
            productSaleDtos.add(new ProductSaleDto(productMapper.productToProductDto(productSale.getProduct()),productSale.getDiscount()));
    return  productSaleDtos;
    }
}
