package com.example.apotekasmilje.controller;

import com.example.apotekasmilje.dto.EvaluationDto;
import com.example.apotekasmilje.dto.ProductDto;
import com.example.apotekasmilje.service.ProductEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/evaluation", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductEvaluationController {
    @Autowired
    private ProductEvaluationService productEvaluationService;

    @GetMapping("/all/{pageNo}/{pageSize}/{id}")
    public ResponseEntity<List<EvaluationDto>> allProductTypes(@PathVariable int pageNo, @PathVariable int pageSize,@PathVariable Long id) {
        return new ResponseEntity<>(productEvaluationService.getAll(pageNo, pageSize, id), HttpStatus.OK);
    }
    @GetMapping("/avg/{id}")
    public ResponseEntity<Float> evaluationAvgByProductId(@PathVariable Long id) {
        return new ResponseEntity<>(productEvaluationService.evaluationAvgByProductId(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('Master_Of_Pharmacy')")
    @GetMapping("/all/{pageNo}/{pageSize}")
    public ResponseEntity<List<EvaluationDto>> getAll(@PathVariable int pageNo, @PathVariable int pageSize) {
        return new ResponseEntity<>(productEvaluationService.findAll(pageNo,pageSize), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('Authenticated_User')")
    @PostMapping("/add")
    public ResponseEntity<String> addEvaluation(@RequestBody EvaluationDto evaluationDto)  {

            if(productEvaluationService.save(evaluationDto))
                return  new ResponseEntity<>("Успјешно креирали евалуацију!", HttpStatus.OK);
            return  new ResponseEntity<>("Евалуација није креирана!", HttpStatus.BAD_REQUEST);
    }
    @PreAuthorize("hasRole('Master_Of_Pharmacy')")
    @PostMapping("/update")
    public ResponseEntity<String> updateEvaluation(@RequestBody EvaluationDto evaluationDto)  {

        if(productEvaluationService.update(evaluationDto))
            return  new ResponseEntity<>("Успјешно одобрили евалуацију!", HttpStatus.OK);
        return  new ResponseEntity<>("Евалуација није одобрена!", HttpStatus.BAD_REQUEST);

    }
    @PreAuthorize("hasRole('Master_Of_Pharmacy')")
    @PostMapping("/delete")
    public ResponseEntity<String> deleteEvaluation(@RequestBody EvaluationDto evaluationDto)  {

        if(productEvaluationService.delete(evaluationDto))
            return  new ResponseEntity<>("Успјешно одбили евалуацију!", HttpStatus.OK);
        return  new ResponseEntity<>("Евалуација није одбијена!", HttpStatus.BAD_REQUEST);

    }
}
