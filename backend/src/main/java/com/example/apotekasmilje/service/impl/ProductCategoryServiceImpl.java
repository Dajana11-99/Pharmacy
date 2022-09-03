package com.example.apotekasmilje.service.impl;

import com.example.apotekasmilje.dto.ProductCategoryDto;
import com.example.apotekasmilje.dto.ProductTypeDto;
import com.example.apotekasmilje.mapper.ProductCategoryMapper;
import com.example.apotekasmilje.model.products.ProductCategory;
import com.example.apotekasmilje.model.products.ProductType;
import com.example.apotekasmilje.repository.ProductCatgoryRepository;
import com.example.apotekasmilje.service.ProductCategoryService;
import com.example.apotekasmilje.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCatgoryRepository productCatgoryRepository;
    @Autowired
    private ProductTypeService productTypeService;

    private ProductCategoryMapper productCategoryMapper = new ProductCategoryMapper();

    public List<ProductCategoryDto> productCategories(int pageNo,int pageSize){
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<ProductCategory> pagedResult = productCatgoryRepository.findAll(paging);
        return productCategoryMapper
                .productCategoriesToProductCategoryDtos(pagedResult.toList());
    }


    public boolean add(ProductCategoryDto productCategoryDto) {
        try {
            ProductType productType = productTypeService
                    .findById(Long.parseLong(productCategoryDto.getType()));
            if (productType == null) return false;
            if (productCatgoryRepository
                    .findByName(productCategoryDto.getName()) != null) return false;
            ProductCategory productCategory = productCategoryMapper
                    .productCategoryDtoToProductCategory(productCategoryDto);
            productCategory.setProductType(productType);
            productCatgoryRepository.save(productCategory);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean update(ProductCategoryDto productCategoryDto) {
        try {
            ProductCategory productCategory=productCatgoryRepository
                    .findById(productCategoryDto.getId()).get();
            if(productCategory==null)return false;
            productCategory.setName(productCategoryDto.getName());
            productCategory.setDescription(productCategoryDto.getDescription());
            productCatgoryRepository.save(productCategory);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public  boolean delete(ProductCategoryDto productCategoryDto){
        try {
            if(productCatgoryRepository
                    .findById(productCategoryDto.getId()).get()==null)
                return false;
            productCatgoryRepository.deleteById(productCategoryDto.getId());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<ProductCategoryDto> searchCategory(String search) {
        return productCategoryMapper
         .productCategoriesToProductCategoryDtos(productCatgoryRepository
         .searchByNameAndProductType(search));
    }

    @Override
    public ProductCategory findById(Long id) {
        return productCatgoryRepository.findById(id).get();
    }

    public List<ProductCategoryDto> findByType(Long id){
       return productCategoryMapper
                .productCategoriesToProductCategoryDtos(productCatgoryRepository.findByTypeId(id));
    }
}
