package com.example.apotekasmilje.service;

import com.example.apotekasmilje.dto.BasketProductsDto;
import com.example.apotekasmilje.dto.ProductDto;
import com.example.apotekasmilje.dto.SearchDto;
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
    List<ProductDto> findByCategory(int pageNo,int pageSize,Long id);
    List<BasketProductsDto> checkProductQuantity(List<BasketProductsDto> basketProductsDtos);

    ProductDto findById(Long id);
    Product findProductById(Long id);

    List<ProductDto> sort(int pageNo, int pageSize, String sort,Long id);

    List<ProductDto> filterProduct(SearchDto searchDto);
}
