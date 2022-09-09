package com.example.apotekasmilje.service.impl;

import com.example.apotekasmilje.dto.CharacteristicsDto;

import com.example.apotekasmilje.dto.ProductDto;
import com.example.apotekasmilje.mapper.CharacteristicsMapper;
import com.example.apotekasmilje.mapper.ImageMapper;
import com.example.apotekasmilje.mapper.ProductInformationMapper;
import com.example.apotekasmilje.mapper.ProductMapper;
import com.example.apotekasmilje.model.products.Characteristics;
import com.example.apotekasmilje.model.products.Image;
import com.example.apotekasmilje.model.products.Product;
import com.example.apotekasmilje.model.products.ProductCategory;
import com.example.apotekasmilje.repository.ProductRepository;
import com.example.apotekasmilje.service.CharacteristicsService;
import com.example.apotekasmilje.service.FirebaseService;
import com.example.apotekasmilje.service.ProductCategoryService;
import com.example.apotekasmilje.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCategoryService productCategoryService;
    private ProductMapper productMapper = new ProductMapper();
    private CharacteristicsMapper characteristicsMapper = new CharacteristicsMapper();

    @Autowired
    private CharacteristicsService characteristicsService;
    private ProductInformationMapper productInformationMapper = new ProductInformationMapper();

    private ImageMapper imageMapper =new ImageMapper();
    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    public boolean add(ProductDto productDto){
        try {
            ProductCategory productCategory = productCategoryService.findById(productDto.getIdCategory());
            if(productRepository.findByName(productDto.getName())!=null)return false;
            Product product = productMapper.productDtoToProduct(productDto);
            product.setProductCategory(productCategory);
            Product saved=productRepository.save(product);
            if(productDto.getCharacteristics().size()!=0)
                setCharacteristics(productDto.getCharacteristics(),saved);
            return true;
        }catch (Exception e){
            return false;
        }

    }
    @Override
    public List<ProductDto> getAll(int pageNo, int pageSize) {
            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("name"));
            Page<Product> pagedResult = productRepository.findAll(paging);
            return productMapper.productsToProductDtos(pagedResult.toList());
    }

    @Override
    public void addNewImage(String name, Image image) {
        Product product = productRepository.findByName(name);
        List<Image> currentImages=product.getImage();
        currentImages.add(image);
        productRepository.save(product);
    }

    private void setCharacteristics(List<CharacteristicsDto> characteristics,Product product){
        product.setCharacteristics(characteristicsMapper
                .characteristicsDtoToCharacteristics(characteristics,product));
        productRepository.save(product);
    }

    public boolean delete(ProductDto productDto){
        try{
            Product product=productRepository.findById(productDto.getId()).get();
            if(product==null)return false;
            productRepository.deleteById(productDto.getId());
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public List<ProductDto> searchByNameAndBrand(String name){
        return productMapper
        .productsToProductDtos(productRepository.searchByNameAndBrand(name));
    }
    @Override
    public boolean update(ProductDto productDto) {
        try {
            Product product=productRepository.findById(productDto.getId()).get();
            if(product==null)return false;
            product.setQuantity(productDto.getQuantity());
            product.setPrice(productDto.getPrice());
            product.setName(productDto.getName());
            product.setImage(imageMapper.imageDtosToImages(productDto.getImage()));
            product.setExpirationDate(LocalDate.parse(productDto.getExpirationDate()));
            product.getProductInformation().setManufacturer(productDto.getProductInformation().getManufacturer());
            product.getProductInformation().setIngredients(productDto.getProductInformation().getIngredients());
            product.getProductInformation().setDose(productDto.getProductInformation().getDose());
            product.getProductInformation().setBrand(productDto.getProductInformation().getBrand());
            product.getProductInformation().setDescription(productDto.getProductInformation().getDescription());
            product.getProductInformation().setCharacteristics(productDto.getProductInformation().getCharacteristics());
            characteristicsService.deleteCharacteristics(product.getId());
            product.setCharacteristics(characteristicsMapper
                    .characteristicsDtoToCharacteristics(productDto.getCharacteristics(),product));
            productRepository.save(product);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<ProductDto> findByCategory(int pageNo,int pageSize,Long id) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("name"));
        return productMapper.productsToProductDtos(productRepository.findByCategory(id,paging));
    }

    @Override
    public ProductDto findById(Long id) {

        return productMapper
        .productToProductDto(productRepository.findProductById(id));
    }

}
