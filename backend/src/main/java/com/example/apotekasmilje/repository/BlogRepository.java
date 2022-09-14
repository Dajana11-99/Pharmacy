package com.example.apotekasmilje.repository;

import com.example.apotekasmilje.model.blog.Blog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlogRepository extends PagingAndSortingRepository<Blog, Long> {
    @Query("SELECT m FROM Blog m  WHERE m.title = :name")
    Blog findByTitle(@Param("name")String name);
    @Query("SELECT m FROM Blog m  WHERE m.pharmacyTechnicians.id = :id")
    List<Blog> findByUser(@Param("id")Long id, Pageable pageable);
}
