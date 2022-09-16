package com.example.apotekasmilje.mapper;

import com.example.apotekasmilje.dto.AnswerDto;
import com.example.apotekasmilje.model.blog.Answer;

import java.util.ArrayList;
import java.util.List;

public class AnswerMapper {

    public Answer answerDtoToAnswer(AnswerDto answerDto){
        return new Answer(answerDto.getId(), answerDto.getText(),null,null);
    }
    public AnswerDto answerToAnswerDto(Answer answer){
        return new AnswerDto(answer.getId(), answer.getText(), null,answer.getQuestion().getId());
    }
    public List<AnswerDto> answersToAnswerDtos(List<Answer>answers){
        List<AnswerDto>answerDtos= new ArrayList<>();
        for(Answer answer:answers)
            answerDtos.add(answerToAnswerDto(answer));
        return answerDtos;
    }
}
