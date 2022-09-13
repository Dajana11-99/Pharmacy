package com.example.apotekasmilje.repository;


import com.example.apotekasmilje.model.products.ProductCategory;
import com.example.apotekasmilje.model.products.Sale;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository  extends PagingAndSortingRepository<Sale, Long> {
    @Query("SELECT m FROM Sale m WHERE  m.name = :name")
    Sale findByName(String name);

    @Query("SELECT m FROM Sale m WHERE  m.start <= :currentDate and m.end >= :currentDate")
    List<Sale> getCurrentSales(@Param("currentDate")LocalDate currentDate, Pageable pageable);
}
