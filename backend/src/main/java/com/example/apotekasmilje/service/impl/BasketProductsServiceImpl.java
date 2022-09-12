package com.example.apotekasmilje.service.impl;

import com.example.apotekasmilje.dto.BasketProductsDto;
import com.example.apotekasmilje.dto.ProductDto;
import com.example.apotekasmilje.mapper.BasketProductsMapper;
import com.example.apotekasmilje.mapper.ProductMapper;
import com.example.apotekasmilje.model.products.BasketProducts;
import com.example.apotekasmilje.model.users.Basket;
import com.example.apotekasmilje.repository.BasketProductsRepository;
import com.example.apotekasmilje.service.BasketProductsService;
import com.example.apotekasmilje.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketProductsServiceImpl implements BasketProductsService {

    @Autowired
    private BasketProductsRepository basketProductsRepository;

    @Autowired
    private BasketService basketService;

    private BasketProductsMapper basketProductsMapper = new BasketProductsMapper();
    public BasketProducts  findByBasketAndProductId(Long basketId,Long productId){
     return   basketProductsRepository.findByBasketAndProductId(basketId,productId);
    }

    public boolean save(BasketProducts basketProducts){

        basketProductsRepository.save(basketProducts);
        return true;
    }

    public List<BasketProductsDto> findProductInBasket(Long id){
        Basket basket= basketService.findByUserId(id);
      return basketProductsMapper.basketProductsToBasketProductDtos(basketProductsRepository
                .findProductInBasket(basket.getId()));
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

}
