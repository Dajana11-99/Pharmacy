package com.example.apotekasmilje.controller;

import com.example.apotekasmilje.dto.PersonDto;
import com.example.apotekasmilje.dto.ProductDto;
import com.example.apotekasmilje.dto.RankDto;
import com.example.apotekasmilje.dto.SearchDto;
import com.example.apotekasmilje.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {

    @Autowired
    private PersonService personService;
    @PreAuthorize("hasRole('Master_Of_Pharmacy')")
    @GetMapping("/all/{pageNo}/{pageSize}")
    public ResponseEntity<List<PersonDto>> allProductTypes(@PathVariable int pageNo, @PathVariable int pageSize)  {
        return  new ResponseEntity<>(personService.getAll(pageNo,pageSize), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('Master_Of_Pharmacy')")
    @PostMapping("/search")
    public ResponseEntity<List<PersonDto>> search(@RequestBody SearchDto searchDto)  {
        return  new ResponseEntity<>(personService.search(searchDto.getSearch()), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('Master_Of_Pharmacy')")
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody PersonDto personDto)  {
        try {
            if(personService.add(personDto))
                return  new ResponseEntity<>("Успјешно регистрован фармацеутски техничар!", HttpStatus.OK);
            return  new ResponseEntity<>("Корисник са тим именом већ постоји!", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return  new ResponseEntity<>("Техничка грешка, покушајте поново касније", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('Master_Of_Pharmacy')")
    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody PersonDto personDto)  {
        try {
            if(personService.delete(personDto))
                return  new ResponseEntity<>("Успјешно обрисан корисник!", HttpStatus.OK);
            return  new ResponseEntity<>("Корисник није обрисан!", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return  new ResponseEntity<>("Техничка грешка, покушајте поново касније", HttpStatus.BAD_REQUEST);
        }
    }
}
