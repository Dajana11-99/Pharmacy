package com.example.apotekasmilje.service.impl;

import com.example.apotekasmilje.dto.BasketDto;
import com.example.apotekasmilje.dto.BasketProductsDto;
import com.example.apotekasmilje.model.products.BasketProductId;
import com.example.apotekasmilje.model.products.BasketProducts;
import com.example.apotekasmilje.model.products.Product;
import com.example.apotekasmilje.model.users.AuthenticatedUser;
import com.example.apotekasmilje.model.users.Basket;
import com.example.apotekasmilje.repository.BasketRepository;
import com.example.apotekasmilje.service.BasketProductsService;
import com.example.apotekasmilje.service.BasketService;
import com.example.apotekasmilje.service.PersonService;
import com.example.apotekasmilje.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BasketServiceImpl implements BasketService {

    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    private PersonService personService;
    @Autowired
    private ProductService productService;
    @Autowired
    private BasketProductsService basketProductsService;

    public boolean addProduct(BasketDto basketDto){
        AuthenticatedUser person = (AuthenticatedUser) personService.findByPersonEmail(basketDto.getUserEmail());
        Basket basket = basketRepository.findByAuthenticatedUser(person.getId());
        if(basket==null) {
            Basket newBasket = new Basket(null, person, new ArrayList<>());
           Basket saved= basketRepository.save(newBasket);
            saveProductInBasket(basketDto,saved);
        }else {
            saveProductInBasket(basketDto, basket);
        }

        return true;
    }
    public Basket findByUserId(Long id){
        return basketRepository.findByAuthenticatedUser(id);
    }

    @Override
    public Boolean updateQuantity(BasketProductsDto basketProductsDto) {
        BasketProducts basketProducts= basketProductsService
                .findByBasketAndProductId(basketProductsDto.getBasketId(),basketProductsDto.getProduct().getId());
        basketProducts.setQuantity(basketProductsDto.getQuantity());
        basketProductsService.save(basketProducts);
        return true;
    }



    @Override
    public Basket save(Basket basket) {
        return basketRepository.save(basket);
    }


    private void saveProductInBasket(BasketDto basketDto, Basket basket){
        BasketProducts basketProducts= basketProductsService
                        .findByBasketAndProductId(basket.getId(),basketDto.getBasketProduct().get(0).getProduct().getId());
        if(basketProducts==null){
            Product product= productService.findProductById(basketDto.getBasketProduct().get(0).getProduct().getId());
          BasketProducts products= new BasketProducts(new BasketProductId(basket.getId(),product.getId()),
                  null,null,basketDto.getBasketProduct().get(0).getQuantity());
          basketProductsService.save(products);
        }else
        {
            basketProducts.setQuantity(basketProducts.getQuantity()+basketDto.getBasketProduct().get(0).getQuantity());
            basketProductsService.save(basketProducts);
        }
    }
}
