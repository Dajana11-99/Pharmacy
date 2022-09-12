package com.example.apotekasmilje.service;

import com.example.apotekasmilje.dto.BasketDto;
import com.example.apotekasmilje.dto.BasketProductsDto;
import com.example.apotekasmilje.model.users.Basket;

public interface BasketService {
    boolean addProduct(BasketDto basketDto);
    Basket findByUserId(Long id);
    Boolean updateQuantity(BasketProductsDto basketProductsDto);
    Basket save(Basket basket);
}
