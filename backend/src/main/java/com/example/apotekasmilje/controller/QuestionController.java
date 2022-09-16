package com.example.apotekasmilje.controller;

import com.example.apotekasmilje.dto.BlogDto;
import com.example.apotekasmilje.dto.ProductTypeDto;
import com.example.apotekasmilje.dto.QuestionDto;
import com.example.apotekasmilje.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/question", produces = MediaType.APPLICATION_JSON_VALUE)
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/findAll/{pageNo}/{pageSize}")
    public ResponseEntity<List<QuestionDto>> findAll(@PathVariable int pageNo, @PathVariable int pageSize) {
        return new ResponseEntity<>(questionService.getAll(pageNo,pageSize), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('Authenticated_User')")
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody QuestionDto questionDto)  {
        try {
            if(questionService.save(questionDto))
                return  new ResponseEntity<>("Успјешно креирано питање!", HttpStatus.OK);
            return  new ResponseEntity<>("Питање није креирано!", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return  new ResponseEntity<>("Техничка грешка, покушајте поново касније", HttpStatus.BAD_REQUEST);
        }
    }
}
