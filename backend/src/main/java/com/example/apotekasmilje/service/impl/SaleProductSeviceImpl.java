package com.example.apotekasmilje.service.impl;

import com.example.apotekasmilje.model.products.ProductSale;
import com.example.apotekasmilje.model.products.Sale;
import com.example.apotekasmilje.repository.SaleProductRepository;
import com.example.apotekasmilje.service.SaleProductService;
import com.example.apotekasmilje.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
public class SaleProductSeviceImpl implements SaleProductService {

    @Autowired
    private SaleProductRepository saleProductRepository;

    @Autowired
    private SaleService saleService;
    public ProductSale checkDoesProductOnSale(Long id){
        return saleProductRepository.checkDoesProductOnSale(id, LocalDate.now());
    }

    public ProductSale findByProductAndSale(Long productId,Long saleId){
        return saleProductRepository.findByProductAndSale(productId,saleId);
    }

    @Override
    @Transactional
    public void deleteProductFromSale(Long productId, Long saleId) {
        saleProductRepository.deleteProductFromSale(productId,saleId);
        saleService.checkSale(saleId);
    }


}
