package com.example.apotekasmilje.service;

import com.example.apotekasmilje.dto.QuestionDto;
import com.example.apotekasmilje.model.blog.Question;

import java.util.List;

public interface QuestionService {
    boolean save(QuestionDto questionDto);
    List<QuestionDto> getAll(int pageNo, int pageSize);

    Question findById(Long id);
}
