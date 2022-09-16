package com.example.apotekasmilje.controller;

import com.example.apotekasmilje.dto.AnswerDto;
import com.example.apotekasmilje.dto.QuestionDto;
import com.example.apotekasmilje.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/answer", produces = MediaType.APPLICATION_JSON_VALUE)
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping ("/findAll")
    public ResponseEntity<List<AnswerDto>> findAll(@RequestBody QuestionDto questionDto) {
        return new ResponseEntity<>(answerService.getAll(questionDto.getId()), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('Pharmacy_Technicians')")
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody AnswerDto answerDto)  {
        try {
            if(answerService.save(answerDto))
                return  new ResponseEntity<>("Успјешно креиран одговор на питање!", HttpStatus.OK);
            return  new ResponseEntity<>("Одговор није креиран!", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return  new ResponseEntity<>("Техничка грешка, покушајте поново касније", HttpStatus.BAD_REQUEST);
        }
    }
}
