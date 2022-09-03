package com.example.apotekasmilje.repository;

import com.example.apotekasmilje.model.products.ProductType;
import com.example.apotekasmilje.model.users.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
    @Query("SELECT m FROM ProductType m WHERE  m.name = :name")
    ProductType findByName(String name);
}
