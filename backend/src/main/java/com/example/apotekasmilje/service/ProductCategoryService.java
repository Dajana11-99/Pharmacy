package com.example.apotekasmilje.service;

import com.example.apotekasmilje.dto.ProductCategoryDto;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategoryDto>productCategories(int pageNo,int pageSize);
    boolean add(ProductCategoryDto productCategoryDto);
    boolean update(ProductCategoryDto productCategoryDto);
    boolean delete(ProductCategoryDto productCategoryDto);

    List<ProductCategoryDto> searchCategory(String search);
}
