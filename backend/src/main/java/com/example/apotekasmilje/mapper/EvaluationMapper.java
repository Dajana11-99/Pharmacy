package com.example.apotekasmilje.mapper;

import com.example.apotekasmilje.dto.EvaluationDto;
import com.example.apotekasmilje.model.products.ProductEvaluation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EvaluationMapper {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
    public ProductEvaluation evaluationDtoToEvaluation(EvaluationDto evaluationDto){
        return new ProductEvaluation(evaluationDto.getId(), evaluationDto.getComment(), evaluationDto.getGrade(),false,null,null,
                LocalDate.now());
    }

    public EvaluationDto evaluationToEvaluationDto(ProductEvaluation productEvaluation){
        return new EvaluationDto(productEvaluation.getId(), productEvaluation.getComment(), productEvaluation.getGrade(),
                null,productEvaluation.getAuthenticatedUser().getFirstName()+" "+productEvaluation.getAuthenticatedUser().getLastName(),
                productEvaluation.getDate().format(formatter));
    }
    public List<EvaluationDto>evaluationsToEvaluationDtos(List<ProductEvaluation> evaluations){
        List<EvaluationDto> evaluationDtos= new ArrayList<>();
        for(ProductEvaluation productEvaluation:evaluations)
            evaluationDtos.add(evaluationToEvaluationDto(productEvaluation));
        return evaluationDtos;
    }
}
