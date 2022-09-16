package com.example.apotekasmilje.service;

import com.example.apotekasmilje.dto.AnswerDto;

import java.util.List;

public interface AnswerService {
    List<AnswerDto> getAll(Long id);
    boolean save(AnswerDto answerDto);
}
