package com.example.apotekasmilje.service.impl;

import com.example.apotekasmilje.dto.BasketProductsDto;
import com.example.apotekasmilje.mapper.ProductMapper;
import com.example.apotekasmilje.model.products.BasketProducts;
import com.example.apotekasmilje.model.products.ProductSale;
import com.example.apotekasmilje.model.users.Basket;
import com.example.apotekasmilje.model.users.Person;
import com.example.apotekasmilje.repository.BasketProductsRepository;
import com.example.apotekasmilje.service.BasketProductsService;
import com.example.apotekasmilje.service.BasketService;
import com.example.apotekasmilje.service.PersonService;
import com.example.apotekasmilje.service.SaleProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasketProductsServiceImpl implements BasketProductsService {

    @Autowired
    private BasketProductsRepository basketProductsRepository;

    @Autowired
    private BasketService basketService;
    @Autowired
    private PersonService personService;

    private ProductMapper productMapper = new ProductMapper();

    @Autowired private SaleProductService saleProductService;


    public BasketProducts  findByBasketAndProductId(Long basketId,Long productId){
     return   basketProductsRepository.findByBasketAndProductId(basketId,productId);
    }

    public boolean save(BasketProducts basketProducts){

        basketProductsRepository.save(basketProducts);
        return true;
    }

    public List<BasketProductsDto> findProductInBasket(String email){

        Person person = personService.findByPersonEmail(email);
        Basket basket= basketService.findByUserId(person.getId());
        return convertProducts(basket);
    }

    public void clearBasket(Long id){
    basketProductsRepository.deleteAll(basketProductsRepository.findProductInBasket(id));
    }

    @Override
    public Boolean deleteProduct(BasketProductsDto basketProductsDto) {
        BasketProducts basketProducts= basketProductsRepository
                .findByBasketAndProductId(basketProductsDto.getBasketId(),basketProductsDto.getProduct().getId());
        basketProductsRepository.delete(basketProducts);
        return true;
    }

    private List<BasketProductsDto> convertProducts(Basket basket){
        List<BasketProductsDto> basketProductsDtos = new ArrayList<>();
        for(BasketProducts basketProducts: basketProductsRepository
                .findProductInBasket(basket.getId())) {
            ProductSale productSale = saleProductService.checkDoesProductOnSale(basketProducts.getProduct().getId());
            if (productSale != null) {
                basketProductsDtos.add(new BasketProductsDto(productMapper
                        .productToProductDt(basketProducts.getProduct(), productSale.getDiscount()), basketProducts.getBasket().getId(), basketProducts.getQuantity()));
            } else {
                basketProductsDtos.add(new BasketProductsDto(productMapper
                        .productToProductDto(basketProducts.getProduct()), basketProducts.getBasket().getId(), basketProducts.getQuantity()));
            }
        }
        return basketProductsDtos;
    }

}
