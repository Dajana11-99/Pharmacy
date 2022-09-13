package com.example.apotekasmilje.repository;

import com.example.apotekasmilje.model.products.ProductSale;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface SaleProductRepository   extends PagingAndSortingRepository<ProductSale, Long> {
    @Query("SELECT m FROM Sale s join ProductSale m on s.id=m.sale.id WHERE  m.product.id = :id and  s.start <= :currentDate and s.end >= :currentDate")
    ProductSale checkDoesProductOnSale(@Param("id")Long id, @Param("currentDate")LocalDate currentDate);
}
