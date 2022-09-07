package com.example.apotekasmilje.repository;


import com.example.apotekasmilje.model.products.Characteristics;
import com.example.apotekasmilje.model.products.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductCharacteristicRepository extends JpaRepository<Characteristics, Long> {
    @Query("SELECT m FROM Characteristics m WHERE  m.product.id = :id")
    List<Characteristics> findByProductId(@Param("id")Long id);
}
