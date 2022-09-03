package com.example.apotekasmilje.controller;

import com.example.apotekasmilje.dto.PersonDto;
import com.example.apotekasmilje.dto.ProductTypeDto;
import com.example.apotekasmilje.dto.RankDto;
import com.example.apotekasmilje.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/productType", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductTypeController {
    @Autowired
    private ProductTypeService productTypeService;
    @PreAuthorize("hasRole('Master_Of_Pharmacy')")
    @GetMapping("/all")
    public ResponseEntity<List<ProductTypeDto>> allProductTypes()  {
        try{
            return  new ResponseEntity<>(productTypeService.findAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasRole('Master_Of_Pharmacy')")
    @PostMapping("/add")
    public ResponseEntity<String> addProductType(@RequestBody ProductTypeDto productTypeDto)  {
        try {
            if(productTypeService.add(productTypeDto))
                return  new ResponseEntity<>("Успјешно креиран тип производа!", HttpStatus.OK);
            return  new ResponseEntity<>("Производ са тим именом већ постоји!", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return  new ResponseEntity<>("Техничка грешка, покушајте поново касније", HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasRole('Master_Of_Pharmacy')")
    @PostMapping("/update")
    public ResponseEntity<String> updateProductType(@RequestBody ProductTypeDto productTypeDto)  {
        try {
            if(productTypeService.update(productTypeDto))
                return  new ResponseEntity<>("Успјешно aжуриран тип производа!", HttpStatus.OK);
            return  new ResponseEntity<>("Тип производа није ажуриран!", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return  new ResponseEntity<>("Техничка грешка, покушајте поново касније", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('Master_Of_Pharmacy')")
    @PostMapping("/delete")
    public ResponseEntity<String> deleteProductType(@RequestBody ProductTypeDto productTypeDto)  {
        try {
            if(productTypeService.delete(productTypeDto))
                return  new ResponseEntity<>("Успјешно обрисан тип производа!", HttpStatus.OK);
            return  new ResponseEntity<>("Тип производа није обрисан!", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return  new ResponseEntity<>("Техничка грешка, покушајте поново касније", HttpStatus.BAD_REQUEST);
        }
    }


}
