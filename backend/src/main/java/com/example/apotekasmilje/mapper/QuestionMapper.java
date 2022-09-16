package com.example.apotekasmilje.mapper;

import com.example.apotekasmilje.dto.QuestionDto;
import com.example.apotekasmilje.model.blog.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionMapper {

    public Question questionDtoToQuestion(QuestionDto questionDto){
        return new Question(questionDto.getId(),questionDto.getText(),null,null);
    }

    public QuestionDto questionToQuestionDto(Question question){
        return new QuestionDto(question.getId(),question.getText(),question.blog.getId(),
                question.authenticatedUser.getFirstName()+"  "+question.authenticatedUser.getLastName()
                ,question.authenticatedUser.getPersonEmail());
    }

    public List<QuestionDto> questionsToQuestionDtos(List<Question> questions){
        List<QuestionDto> questionDtos= new ArrayList<>();
        for(Question question:questions)
            questionDtos.add(questionToQuestionDto(question));
        return questionDtos;
    }
}
