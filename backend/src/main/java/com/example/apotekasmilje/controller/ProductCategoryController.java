package com.example.apotekasmilje.controller;

import com.example.apotekasmilje.dto.ProductCategoryDto;
import com.example.apotekasmilje.dto.SearchDto;
import com.example.apotekasmilje.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/productCategory", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @PreAuthorize("hasRole('Master_Of_Pharmacy')")
    @GetMapping("/all/{pageNo}/{pageSize}")
    public ResponseEntity<List<ProductCategoryDto>> allProductTypes(@PathVariable int pageNo, @PathVariable int pageSize)  {
        try{
            return  new ResponseEntity<>(productCategoryService.productCategories(pageNo,pageSize), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/findByType/{id}")
    public ResponseEntity<List<ProductCategoryDto>> findByType(@PathVariable Long id)  {
        try{
            return  new ResponseEntity<>(productCategoryService.findByType(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasRole('Master_Of_Pharmacy')")
    @PostMapping("/searchCategory")
    public ResponseEntity<List<ProductCategoryDto>> searchCategory(@RequestBody SearchDto searchDto)  {
        try{
            return  new ResponseEntity<>(productCategoryService.searchCategory(searchDto.getSearch()), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasRole('Master_Of_Pharmacy')")
    @PostMapping("/add")
    public ResponseEntity<String> addProductType(@RequestBody ProductCategoryDto productCategoryDto)  {
        try {
            if(productCategoryService.add(productCategoryDto))
                return  new ResponseEntity<>("Успјешно  креирана категорија производа!", HttpStatus.OK);
            return  new ResponseEntity<>("Категорија са тим именом већ постоји!", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return  new ResponseEntity<>("Техничка грешка, покушајте поново касније", HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasRole('Master_Of_Pharmacy')")
    @PostMapping("/update")
    public ResponseEntity<String> updateProductType(@RequestBody ProductCategoryDto productCategoryDto)  {
        try {
            if(productCategoryService.update(productCategoryDto))
                return  new ResponseEntity<>("Успјешно  aжурирана категорија производа!", HttpStatus.OK);
            return  new ResponseEntity<>("Категорија није ажурирана!", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return  new ResponseEntity<>("Техничка грешка, покушајте поново касније", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('Master_Of_Pharmacy')")
    @PostMapping("/delete")
    public ResponseEntity<String> deleteProductType(@RequestBody ProductCategoryDto productCategoryDto)  {
        try {
            if(productCategoryService.delete(productCategoryDto))
                return  new ResponseEntity<>("Успјешно обрисана категорија производа!", HttpStatus.OK);
            return  new ResponseEntity<>("Категорије није обрисана!", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return  new ResponseEntity<>("Техничка грешка, покушајте поново касније", HttpStatus.BAD_REQUEST);
        }
    }
}
