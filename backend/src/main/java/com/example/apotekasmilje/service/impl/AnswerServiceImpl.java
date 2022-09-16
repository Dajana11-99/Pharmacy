package com.example.apotekasmilje.service.impl;

import com.example.apotekasmilje.dto.AnswerDto;
import com.example.apotekasmilje.dto.QuestionDto;
import com.example.apotekasmilje.mapper.AnswerMapper;
import com.example.apotekasmilje.model.blog.Answer;
import com.example.apotekasmilje.model.blog.Question;
import com.example.apotekasmilje.model.users.PharmacyTechnicians;
import com.example.apotekasmilje.repository.AnswerRepository;
import com.example.apotekasmilje.service.AnswerService;
import com.example.apotekasmilje.service.PersonService;
import com.example.apotekasmilje.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private PersonService personService;

    public List<AnswerDto> getAll(Long id) {
        return answerMapper.answersToAnswerDtos( answerRepository.findAllByQuestion(id));
    }
    private AnswerMapper answerMapper = new AnswerMapper();
    public boolean save(AnswerDto answerDto){
        try {
            PharmacyTechnicians pharmacyTechnicians=
            (PharmacyTechnicians) personService.findByPersonEmail(answerDto.getPharmacyTechnicians());
            Question question = questionService.findById(answerDto.getQuestionId());
            Answer answer = answerMapper.answerDtoToAnswer(answerDto);
            answer.setQuestion(question);
            answer.setPharmacyTechnicians(pharmacyTechnicians);
            answerRepository.save(answer);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
