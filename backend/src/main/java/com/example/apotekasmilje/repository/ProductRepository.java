package com.example.apotekasmilje.repository;


import com.example.apotekasmilje.model.products.Product;
import com.example.apotekasmilje.model.products.ProductCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Product findByName(String name);
    @Query("SELECT m FROM Product m join  ProductInformation p on m.productInformation.id=p.id" +
            "  WHERE   UPPER(m.name)  LIKE  UPPER(CONCAT(:name, '%')) " +
            " or UPPER(p.brand) LIKE UPPER(CONCAT(:name, '%'))")
    List<Product> searchByNameAndBrand(@Param("name")String name);

    @Query("SELECT m FROM Product m WHERE m.productCategory.id = :id")
    List<Product> findByCategory(@Param("id")Long id ,Pageable peagable);

    @Query("SELECT m FROM Product m WHERE m.id = :id")
    Product findProductById(Long id);
    @Query("SELECT m FROM Product m WHERE m.productCategory.id = :id and m.price BETWEEN :from and :to")
    List<Product> filterProduct(@Param("from")float from,@Param("to") float to, @Param("id") Long categoryId, Pageable paging);

    @Query("SELECT  CASE WHEN  COUNT(m) > 0 THEN true ELSE false END FROM Product m WHERE m.id =:id and m.quantity>= :quantity")
    Boolean checkProductQuantity(@Param("quantity")int quantity,@Param("id")Long id);

}
