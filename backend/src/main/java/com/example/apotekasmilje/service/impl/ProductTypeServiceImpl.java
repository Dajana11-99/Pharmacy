package com.example.apotekasmilje.service.impl;

import com.example.apotekasmilje.dto.ProductTypeDto;
import com.example.apotekasmilje.mapper.ProductTypeMapper;
import com.example.apotekasmilje.model.products.ProductType;
import com.example.apotekasmilje.repository.ProductTypeRepository;
import com.example.apotekasmilje.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    private ProductTypeMapper productTypeMapper = new ProductTypeMapper();

    public List<ProductTypeDto> findAll() {
        return productTypeMapper
                .productTypesToProductTypeDtos(productTypeRepository.findAll());
    }

    public boolean add(ProductTypeDto productTypeDto) {
        try {
            if (productTypeRepository.findByName(productTypeDto.getName()) != null)
                return false;
            ProductType productType = productTypeMapper.productDtoToProduct(productTypeDto);
            productTypeRepository.save(productType);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean update(ProductTypeDto productTypeDto) {
        try {
            ProductType productType = productTypeRepository
                    .findByName(productTypeDto.getName());
            if (productType == null)
                return false;
            productType.setName(productTypeDto.getName());
            productTypeRepository.save(productType);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public  boolean delete(ProductTypeDto productTypeDto){
        try {
            ProductType productType = productTypeRepository
                    .findByName(productTypeDto.getName());
            if (productType == null)
                return false;
            productTypeRepository.delete(productType);
            productTypeRepository.save(productType);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
