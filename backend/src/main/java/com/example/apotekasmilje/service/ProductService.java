package com.example.apotekasmilje.service;

import com.example.apotekasmilje.dto.ProductDto;
import com.example.apotekasmilje.model.products.Image;
import com.example.apotekasmilje.model.products.Product;

import java.util.List;

public interface ProductService {
    Product findByName(String name);
    boolean add(ProductDto productDto);
    List<ProductDto> getAll(int pageNo, int pageSize);
    void addNewImage(String name, Image image);
    boolean delete(ProductDto productDto);
    List<ProductDto> searchByNameAndBrand(String name);
    boolean update(ProductDto productDto);
}
