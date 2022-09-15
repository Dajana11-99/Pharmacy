package com.example.apotekasmilje.repository;

import com.example.apotekasmilje.model.blog.Blog;
import com.example.apotekasmilje.model.enums.Type;
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
    @Query("SELECT m FROM Blog m  WHERE m.status =false")
    List<Blog> findAllUnAccepted(Pageable paging);
    @Query("SELECT m FROM Blog m  WHERE m.type = :type and m.status =true")
    List<Blog> findAllPubishedBlogsByType(@Param("type") Type type, Pageable paging);

    @Query("SELECT m FROM Blog m  WHERE m.status =true")
    List<Blog> findAllAccepted(Pageable paging);
}
