package com.example.apotekasmilje.service.impl;

import com.example.apotekasmilje.dto.ProductCategoryDto;
import com.example.apotekasmilje.dto.ProductDto;
import com.example.apotekasmilje.mapper.CharacteristicsMapper;
import com.example.apotekasmilje.mapper.ProductMapper;
import com.example.apotekasmilje.model.products.Product;
import com.example.apotekasmilje.model.products.ProductCategory;
import com.example.apotekasmilje.repository.ProductRepository;
import com.example.apotekasmilje.service.ProductCategoryService;
import com.example.apotekasmilje.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCategoryService productCategoryService;
    private ProductMapper productMapper = new ProductMapper();
    private CharacteristicsMapper characteristicsMapper = new CharacteristicsMapper();
    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    public boolean add(ProductDto productDto){
        ProductCategory productCategory = productCategoryService.findById(productDto.getIdCategory());
        if(productRepository.findByName(productDto.getName())!=null)return false;
        Product product = productMapper.productDtoToProduct(productDto);
        product.setProductCategory(productCategory);
        if(productDto.getCharacteristics().size()!=0)
            product.setCharacteristics(characteristicsMapper
              .characteristicsDtoToCharacteristics(productDto.getCharacteristics()));
        productRepository.save(product);
        return true;
    }

    @Override
    public List<ProductDto> getAll(int pageNo, int pageSize) {
            Pageable paging = PageRequest.of(pageNo, pageSize);
            Page<Product> pagedResult = productRepository.findAll(paging);
            return productMapper.productsToProductDtos(pagedResult.toList());
    }

}
