package com.example.apotekasmilje.repository;


import com.example.apotekasmilje.model.products.ProductCategory;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductCatgoryRepository  extends PagingAndSortingRepository<ProductCategory, Long> {

    @Query("SELECT m FROM ProductCategory m WHERE  m.name = :name")
    ProductCategory findByName(String name);

    @Query("SELECT m FROM ProductCategory m join  ProductType p on m.productType.id=p.id" +
            "  WHERE   UPPER(m.name)  LIKE  UPPER(CONCAT(:name, '%')) " +
            " or UPPER(p.name) LIKE UPPER(CONCAT(:name, '%'))")
    List<ProductCategory> searchByNameAndProductType(@Param("name")String name);
}
