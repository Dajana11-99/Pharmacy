package com.example.apotekasmilje.service;

import com.example.apotekasmilje.model.products.ProductSale;

public interface SaleProductService {
    ProductSale checkDoesProductOnSale(Long id);
    ProductSale findByProductAndSale(Long productId,Long saleId);
    void deleteProductFromSale(Long productId,Long saleId);


}
