package com.example.apotekasmilje.mapper;

import com.example.apotekasmilje.dto.ProductCategoryDto;
import com.example.apotekasmilje.model.products.ProductCategory;

import java.util.ArrayList;
import java.util.List;

public class ProductCategoryMapper {

    public ProductCategory productCategoryDtoToProductCategory(ProductCategoryDto productCategoryDto){
        return  new ProductCategory(productCategoryDto.getId(), productCategoryDto.getName(), productCategoryDto.getDescription(), null);
    }

    public  ProductCategoryDto productCategoryToProductCategoryDto(ProductCategory productCategory){
        return  new ProductCategoryDto(productCategory.getId(), productCategory.getName(), productCategory.getDescription(), productCategory.getProductType().getName());
    }
    public  List<ProductCategoryDto> productCategoriesToProductCategoryDtos(List<ProductCategory> categories){
        List<ProductCategoryDto> categoryDtos = new ArrayList<>();
        for(ProductCategory productCategory: categories)
            categoryDtos.add(productCategoryToProductCategoryDto(productCategory));
        return  categoryDtos;
    }
}
