package com.example.apotekasmilje.service;

import com.example.apotekasmilje.dto.EvaluationDto;

import java.util.List;

public interface ProductEvaluationService {
    boolean save(EvaluationDto evaluationDto);

    List<EvaluationDto> getAll(int pageNo, int pageSize,Long id);

    List<EvaluationDto> findAll(int pageNo,int pageSize);

    boolean update(EvaluationDto evaluationDto);

    boolean delete(EvaluationDto evaluationDto);

    Float evaluationAvgByProductId(Long id);
}
