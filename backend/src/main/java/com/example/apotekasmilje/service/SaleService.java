package com.example.apotekasmilje.service;

import com.example.apotekasmilje.dto.SaleDto;
import com.example.apotekasmilje.model.products.ProductSale;

import java.util.List;

public interface SaleService {
    Boolean addSale(SaleDto saleDto);

    List<SaleDto> getCurrentSales(int pageNo, int pageSize);

    void checkSale(Long saleId);
}
