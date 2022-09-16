package com.example.apotekasmilje.repository;

import com.example.apotekasmilje.model.blog.Answer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends PagingAndSortingRepository<Answer, Long> {
    @Query("SELECT b  FROM  Answer  b WHERE b.question.id  = :id")
    List<Answer> findAllByQuestion(@Param("id")Long id);
}
