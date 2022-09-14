package com.example.apotekasmilje.controller;

import com.example.apotekasmilje.dto.BlogDto;
import com.example.apotekasmilje.dto.ComplaintDto;
import com.example.apotekasmilje.dto.OrderInformationDto;
import com.example.apotekasmilje.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/blog", produces = MediaType.APPLICATION_JSON_VALUE)
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/save")
    @PreAuthorize("hasRole('Pharmacy_Technicians')")
    public ResponseEntity<String> save(@RequestBody BlogDto blogDto)  {
        if(blogService.save(blogDto))
            return  new ResponseEntity<>("Успјешно сте креирали блог!", HttpStatus.OK);
        return  new ResponseEntity<>("Блог са тим насловом већ постоји!", HttpStatus.BAD_REQUEST);
    }
    @PreAuthorize("hasRole('Pharmacy_Technicians')")
    @GetMapping("/findByUser/{pageNo}/{pageSize}/{email}/")
    public ResponseEntity<List<BlogDto>> findByUser(@PathVariable int pageNo, @PathVariable int pageSize, @PathVariable String email) {
        return new ResponseEntity<>(blogService.findByUser(pageNo,pageSize,email), HttpStatus.OK);
    }


}
