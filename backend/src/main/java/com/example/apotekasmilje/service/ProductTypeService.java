package com.example.apotekasmilje.service;

import com.example.apotekasmilje.dto.ProductTypeDto;
import com.example.apotekasmilje.model.products.ProductType;

import java.util.List;

public interface ProductTypeService {
    List<ProductTypeDto> findAll();
    boolean add(ProductTypeDto productTypeDto);
    boolean update(ProductTypeDto productTypeDto);
    boolean delete(ProductTypeDto productTypeDto);
    ProductType findById(Long id);
    ;}
