package com.example.apotekasmilje.mapper;

import com.example.apotekasmilje.dto.SaleDto;
import com.example.apotekasmilje.model.products.Sale;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SaleMapper {
    private SaleProductsMapper saleProductsMapper  = new SaleProductsMapper();
    public Sale saleDtoToSale(SaleDto saleDto){
        return  new Sale(null,saleDto.getName(), LocalDate.parse(saleDto.getStart()),LocalDate.parse(saleDto.getEnd()),null);
    }

    public  SaleDto saleToSaleDto(Sale sale){
        return new SaleDto(sale.getId(),sale.getName(),sale.getStart().toString(),sale.getEnd().toString()
        ,saleProductsMapper.productSaleToProductSaleDtos(sale.getProductSales()));
    }

    public List<SaleDto> salesToSaleDtos(List<Sale> sales){
        List<SaleDto> saleDtos = new ArrayList<>();
        for(Sale sale:sales)
            saleDtos.add(saleToSaleDto(sale));
        return saleDtos;
    }
}
