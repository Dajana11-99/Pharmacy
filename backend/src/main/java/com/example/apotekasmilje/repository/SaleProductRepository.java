package com.example.apotekasmilje.repository;

import com.example.apotekasmilje.model.products.ProductSale;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;

public interface SaleProductRepository   extends PagingAndSortingRepository<ProductSale, Long> {
    @Query("SELECT m FROM Sale s join ProductSale m on s.id=m.sale.id WHERE  m.product.id = :id and  s.start <= :currentDate and s.end >= :currentDate")
    ProductSale checkDoesProductOnSale(@Param("id")Long id, @Param("currentDate")LocalDate currentDate);
    @Query("SELECT m FROM ProductSale m WHERE m.sale.id=:sale_id and m.product.id=:product_id")
    ProductSale findByProductAndSale(@Param("product_id")Long productId, @Param("sale_id")Long saleId );
    @Modifying
    @Transactional
    @Query("DELETE FROM ProductSale m WHERE m.sale.id=:sale_id and m.product.id=:product_id")
    void deleteProductFromSale(@Param("product_id")Long productId, @Param("sale_id")Long saleId );
}
