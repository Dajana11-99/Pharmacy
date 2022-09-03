package com.example.apotekasmilje.controller;

import com.example.apotekasmilje.dto.ProductCategoryDto;
import com.example.apotekasmilje.dto.ProductDto;
import com.example.apotekasmilje.dto.ProductTypeDto;
import com.example.apotekasmilje.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    @PreAuthorize("hasRole('Master_Of_Pharmacy')")
    @GetMapping("/all/{pageNo}/{pageSize}")
    public ResponseEntity<List<ProductDto>> allProductTypes(@PathVariable int pageNo, @PathVariable int pageSize)  {
        try{
            return  new ResponseEntity<>(productService.getAll(pageNo,pageSize), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @Autowired
    private ProductService productService;
    @PreAuthorize("hasRole('Master_Of_Pharmacy')")
    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody ProductDto productDto)  {
        try {
            if(productService.add(productDto))
                return  new ResponseEntity<>("Успјешно креираn производ!", HttpStatus.OK);
            return  new ResponseEntity<>("Производ са тим именом већ постоји!", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return  new ResponseEntity<>("Техничка грешка, покушајте поново касније", HttpStatus.BAD_REQUEST);
        }
    }
}
