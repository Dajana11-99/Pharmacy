package com.example.apotekasmilje.repository;


import com.example.apotekasmilje.model.products.Product;
import com.example.apotekasmilje.model.products.ProductCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Product findByName(String name);
    @Query("SELECT m FROM Product m join  ProductInformation p on m.productInformation.id=p.id" +
            "  WHERE   UPPER(m.name)  LIKE  UPPER(CONCAT(:name, '%')) " +
            " or UPPER(p.brand) LIKE UPPER(CONCAT(:name, '%'))")
    List<Product> searchByNameAndBrand(@Param("name")String name);


}
