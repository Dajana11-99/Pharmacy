package com.example.apotekasmilje.service.impl;

import com.example.apotekasmilje.dto.BasketProductsDto;
import com.example.apotekasmilje.dto.CharacteristicsDto;

import com.example.apotekasmilje.dto.ProductDto;
import com.example.apotekasmilje.dto.SearchDto;
import com.example.apotekasmilje.mapper.CharacteristicsMapper;
import com.example.apotekasmilje.mapper.ImageMapper;
import com.example.apotekasmilje.mapper.ProductInformationMapper;
import com.example.apotekasmilje.mapper.ProductMapper;
import com.example.apotekasmilje.model.products.Image;
import com.example.apotekasmilje.model.products.Product;
import com.example.apotekasmilje.model.products.ProductCategory;
import com.example.apotekasmilje.model.products.ProductSale;
import com.example.apotekasmilje.repository.ProductRepository;
import com.example.apotekasmilje.service.*;
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
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CharacteristicsService characteristicsService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private SaleProductService saleProductService;
    private ProductMapper productMapper = new ProductMapper();
    private CharacteristicsMapper characteristicsMapper = new CharacteristicsMapper();

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
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product:  productRepository.searchByNameAndBrand(name)){
            ProductSale productSale=saleProductService.checkDoesProductOnSale(product.getId());
            if(productSale!=null){
                productDtos.add(productMapper.productToProductDt(product,productSale.getDiscount()));
            }else {
                productDtos.add(productMapper.productToProductDto(product));
            }
        }
        return productDtos;

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
        List<ProductDto> productDtos = new ArrayList<>();
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("name"));
        for(Product product: productRepository.findByCategory(id,paging)){
            ProductSale productSale=saleProductService.checkDoesProductOnSale(product.getId());
            if(productSale!=null){
                productDtos.add(productMapper.productToProductDt(product,productSale.getDiscount()));
            }else {
                productDtos.add(productMapper.productToProductDto(product));
            }
        }
        return productDtos;
    }

    @Override
    public ProductDto findById(Long id) {
         Product product= productRepository.findProductById(id);
            ProductSale productSale=saleProductService.checkDoesProductOnSale(product.getId());
            if(productSale!=null){
                return productMapper.productToProductDt(product,productSale.getDiscount());
            }else {
                return  productMapper.productToProductDto(product);
            }
    }
    @Override
    public Product findProductById(Long id) {

        return productRepository.findProductById(id);
    }

    @Override
    public List<ProductDto> sort(int pageNo, int pageSize, String sort,Long id) {
        Pageable paging;

        if(Integer.parseInt(sort)==1){
             paging = PageRequest.of(pageNo, pageSize, Sort.by("price").ascending());
        }else if(Integer.parseInt(sort)==2){
             paging = PageRequest.of(pageNo, pageSize, Sort.by("price").descending());
        }else if(Integer.parseInt(sort)==3){
            return null;
        }else {
             paging = PageRequest.of(pageNo, pageSize, Sort.by("name"));
        }
        return convertProduct(id,paging);
    }

  private List<ProductDto> convertProduct(Long id,Pageable paging){
      List<ProductDto> productDtos = new ArrayList<>();
      for(Product product:  productRepository.findByCategory(id,paging)){
          ProductSale productSale=saleProductService.checkDoesProductOnSale(product.getId());
          if(productSale!=null){
              productDtos.add(productMapper.productToProductDt(product,productSale.getDiscount()));
          }else {
              productDtos.add(productMapper.productToProductDto(product));
          }
      }
      return productDtos;
  }

    @Override
    public List<ProductDto> filterProduct(SearchDto searchDto) {
        Pageable paging = PageRequest.of(searchDto.getPageNo(), searchDto.getPageSize(),Sort.by("price").ascending());
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product: productRepository
                .filterProduct(searchDto.getFrom(),searchDto.getTo(), searchDto.getCategoryId(), paging))
        {
            ProductSale productSale=saleProductService.checkDoesProductOnSale(product.getId());
            if(productSale!=null){
                productDtos.add(productMapper.productToProductDt(product,productSale.getDiscount()));
            }else {
                productDtos.add(productMapper.productToProductDto(product));
            }
        }
        return productDtos;

    }

    public List<BasketProductsDto> checkProductQuantity(List<BasketProductsDto> basketProductsDtos){
        List<BasketProductsDto> res= new ArrayList<>();
        for(BasketProductsDto basketProductsDto:basketProductsDtos){
            if(!productRepository
            .checkProductQuantity(basketProductsDto.getQuantity(),basketProductsDto.getProduct().getId())){
                res.add(basketProductsDto);
            }
        }
        return res;
    }


}
