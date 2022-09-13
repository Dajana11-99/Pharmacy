package com.example.apotekasmilje.service.impl;

import com.example.apotekasmilje.model.products.ProductSale;
import com.example.apotekasmilje.repository.SaleProductRepository;
import com.example.apotekasmilje.service.SaleProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SaleProductSeviceImpl implements SaleProductService {

    @Autowired
    private SaleProductRepository saleProductRepository;

    public ProductSale checkDoesProductOnSale(Long id){
        return saleProductRepository.checkDoesProductOnSale(id, LocalDate.now());
    }
}
