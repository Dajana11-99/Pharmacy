package com.example.apotekasmilje.repository;


import com.example.apotekasmilje.model.products.ProductEvaluation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductEvaluationRepository  extends PagingAndSortingRepository<ProductEvaluation, Long> {
    @Query("SELECT m FROM ProductEvaluation m WHERE m.product.id=:id and m.status=true")
    List<ProductEvaluation> findByProductId(@Param("id")Long id, Pageable paging);

    @Query("SELECT m FROM ProductEvaluation m WHERE m.status=false")
    Page<ProductEvaluation> findAll(Pageable paging);

    @Query("SELECT avg (m.grade) FROM ProductEvaluation m WHERE m.product.id=:id and m.status=true")
    Float evaluationAvgByProductId(@Param("id")Long id);
}
