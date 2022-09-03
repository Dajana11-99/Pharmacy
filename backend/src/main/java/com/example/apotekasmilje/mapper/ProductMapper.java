package com.example.apotekasmilje.mapper;

import com.example.apotekasmilje.dto.ProductDto;
import com.example.apotekasmilje.model.products.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductMapper {

    private  ProductInformationMapper productInformationMapper = new ProductInformationMapper();
    public Product productDtoToProduct(ProductDto productDto){
        return new Product(productDto.getId(), productDto.getName(),productDto.getPrice(),
                LocalDate.parse(productDto.getExpirationDate()), productDto.getQuantity(), false,null,
               productInformationMapper.productInformationDtoToProductInformation(productDto.getProductInformation()),null,null,
                null);
    }
    public ProductDto productToProductDto(Product product){
        return  new ProductDto(product.getId(), product.getName(), product.getPrice(),product.getExpirationDate().toString()
        , product.getQuantity(),product.isOnSale(),null,productInformationMapper.productInformationToProductInformationDto(product.getProductInformation()),
                product.getProductCategory().getId(),null);
    }
    public List<ProductDto> productsToProductDtos(List<Product> products){
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product: products)
            productDtos.add(productToProductDto(product));
        return productDtos;
    }
}
