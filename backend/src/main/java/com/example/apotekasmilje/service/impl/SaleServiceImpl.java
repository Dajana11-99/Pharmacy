package com.example.apotekasmilje.service.impl;

import com.example.apotekasmilje.dto.ProductSaleDto;
import com.example.apotekasmilje.dto.SaleDto;
import com.example.apotekasmilje.mapper.SaleMapper;
import com.example.apotekasmilje.model.products.ProductSale;
import com.example.apotekasmilje.model.products.ProductSaleId;
import com.example.apotekasmilje.model.products.Sale;
import com.example.apotekasmilje.model.users.Person;
import com.example.apotekasmilje.repository.SaleRepository;
import com.example.apotekasmilje.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;

    private SaleMapper saleMapper = new SaleMapper();

    public Boolean addSale(SaleDto saleDto){
        if(saleRepository.findByName(saleDto.getName())!=null)return false;
        Sale sale = saleMapper.saleDtoToSale(saleDto);
        Sale saved=saleRepository.save(sale);
        addProductsOnSale(saleDto,saved);
        return true;
    }

    @Override
    public List<SaleDto> getCurrentSales(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("end").descending());
        List<Sale> pagedResult = saleRepository.getCurrentSales(LocalDate.now(),paging);
        return saleMapper.salesToSaleDtos(pagedResult);
    }

    private void addProductsOnSale(SaleDto saleDto,Sale sale) {
        List<ProductSale> productSale = new ArrayList<>();
        for(ProductSaleDto productSaleDto: saleDto.getProduct())
            productSale.add(new ProductSale(new ProductSaleId(sale.getId(),
                    productSaleDto.getProduct().getId()),null,null,productSaleDto.getDiscount()));
        sale.setProductSales(productSale);
        saleRepository.save(sale);
    }
}
