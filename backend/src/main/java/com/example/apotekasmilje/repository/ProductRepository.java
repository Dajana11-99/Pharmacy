package com.example.apotekasmilje.repository;


import com.example.apotekasmilje.model.products.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Product findByName(String name);
}
