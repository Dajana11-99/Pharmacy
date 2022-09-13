package com.example.apotekasmilje.service;

import com.example.apotekasmilje.model.products.ProductSale;

public interface SaleProductService {
    ProductSale checkDoesProductOnSale(Long id);
}
