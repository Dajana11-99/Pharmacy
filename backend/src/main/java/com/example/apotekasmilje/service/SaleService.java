package com.example.apotekasmilje.service;

import com.example.apotekasmilje.dto.SaleDto;

import java.util.List;

public interface SaleService {
    Boolean addSale(SaleDto saleDto);

    List<SaleDto> getCurrentSales(int pageNo, int pageSize);
}
