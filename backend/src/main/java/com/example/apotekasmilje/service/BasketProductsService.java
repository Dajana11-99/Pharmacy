package com.example.apotekasmilje.service;

import com.example.apotekasmilje.dto.BasketProductsDto;
import com.example.apotekasmilje.dto.ProductDto;
import com.example.apotekasmilje.model.products.BasketProducts;

import java.util.List;

public interface BasketProductsService {
    BasketProducts findByBasketAndProductId(Long basketId, Long productId);
    boolean save(BasketProducts basketProducts);
    List<BasketProductsDto> findProductInBasket(String email);
    Boolean deleteProduct(BasketProductsDto basketProductsDto);
    void clearBasket(Long id);


}
