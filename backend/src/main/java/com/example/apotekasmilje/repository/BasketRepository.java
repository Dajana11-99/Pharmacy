package com.example.apotekasmilje.repository;


import com.example.apotekasmilje.model.products.Product;
import com.example.apotekasmilje.model.products.ProductCategory;
import com.example.apotekasmilje.model.users.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    @Query("SELECT m FROM Basket m WHERE  m.authenticatedUser.id = :id")
    Basket findByAuthenticatedUser(@Param("id")Long id);




}
