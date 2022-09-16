package com.example.apotekasmilje.service.impl;

import com.example.apotekasmilje.dto.QuestionDto;
import com.example.apotekasmilje.dto.RankDto;
import com.example.apotekasmilje.mapper.QuestionMapper;
import com.example.apotekasmilje.model.blog.Blog;
import com.example.apotekasmilje.model.blog.Question;
import com.example.apotekasmilje.model.users.AuthenticatedUser;
import com.example.apotekasmilje.model.users.Person;
import com.example.apotekasmilje.model.users.Rank;
import com.example.apotekasmilje.repository.QuestionRepository;
import com.example.apotekasmilje.service.BlogService;
import com.example.apotekasmilje.service.PersonService;
import com.example.apotekasmilje.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private PersonService personService;

    private QuestionMapper questionMapper = new QuestionMapper();

    @Autowired
    private BlogService blogService;
    public boolean save(QuestionDto questionDto){
        try {
            AuthenticatedUser person = (AuthenticatedUser) personService.findByPersonEmail(questionDto.getUserEmail());
            Blog blog = blogService.findById(questionDto.getBlogId());
            Question question= questionMapper.questionDtoToQuestion(questionDto);
            question.setBlog(blog);
            question.setAuthenticatedUser(person);
            questionRepository.save(question);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public List<QuestionDto> getAll(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Question> pagedResult = questionRepository.findAll(paging);
        return questionMapper.questionsToQuestionDtos(pagedResult.toList());
    }

    @Override
    public Question findById(Long id) {
        return questionRepository.findById(id).get();
    }
}
