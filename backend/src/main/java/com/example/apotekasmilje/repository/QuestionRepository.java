package com.example.apotekasmilje.repository;

import com.example.apotekasmilje.model.blog.Question;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface QuestionRepository extends PagingAndSortingRepository<Question, Long> {

}
