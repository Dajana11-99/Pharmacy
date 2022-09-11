package com.example.apotekasmilje.repository;

import com.example.apotekasmilje.model.products.BasketProducts;
import com.example.apotekasmilje.model.products.Product;
import com.example.apotekasmilje.model.users.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BasketProductsRepository extends JpaRepository<BasketProducts, Long> {
    @Query("SELECT m FROM BasketProducts m WHERE  m.basket.id = :id and m.product.id= :id_p")
    BasketProducts findByBasketAndProductId(@Param("id")Long basketId,@Param("id_p")Long productId);

    @Query("SELECT b  FROM  BasketProducts  b WHERE b.basket.id  = :id")
    List<BasketProducts> findProductInBasket(@Param("id")Long basketId);
}
