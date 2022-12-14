package com.example.apotekasmilje.controller;


import com.example.apotekasmilje.dto.BasketProductsDto;
import com.example.apotekasmilje.dto.ProductDto;
import com.example.apotekasmilje.dto.SearchDto;
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
    @Autowired
    private ProductService productService;

    @PreAuthorize("hasRole('Master_Of_Pharmacy')")
    @GetMapping("/all/{pageNo}/{pageSize}")
    public ResponseEntity<List<ProductDto>> allProductTypes(@PathVariable int pageNo, @PathVariable int pageSize)  {

            return  new ResponseEntity<>(productService.getAll(pageNo,pageSize), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('Master_Of_Pharmacy')")
    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody ProductDto productDto)  {
        try {
            if(productService.add(productDto))
                return  new ResponseEntity<>("Успјешно креиран производ!", HttpStatus.OK);
            return  new ResponseEntity<>("Производ са тим именом већ постоји!", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return  new ResponseEntity<>("Техничка грешка, покушајте поново касније", HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasRole('Authenticated_User')")
    @PostMapping("/checkQuantity")
    public ResponseEntity<List<BasketProductsDto>> checkQuantity(@RequestBody List<BasketProductsDto> basketProductsDto)  {
        return  new ResponseEntity<>(productService.checkProductQuantity(basketProductsDto), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('Master_Of_Pharmacy')")
    @PostMapping("/update")
    public ResponseEntity<String> updateProduct(@RequestBody ProductDto productDto)  {
        try {
            if(productService.update(productDto))
                return  new ResponseEntity<>("Успјешно креиран производ!", HttpStatus.OK);
            return  new ResponseEntity<>("Производ са тим именом већ постоји!", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return  new ResponseEntity<>("Техничка грешка, покушајте поново касније", HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasRole('Master_Of_Pharmacy')")
    @PostMapping("/delete")
    public ResponseEntity<String> deleteProduct(@RequestBody ProductDto productDto)  {
        try {
            if(productService.delete(productDto))
                return  new ResponseEntity<>("Успјешно обрисан производ!", HttpStatus.OK);
            return  new ResponseEntity<>("Производ није обрисан! Корисник га је наручио.", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return  new ResponseEntity<>("Техничка грешка, покушајте поново касније", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/search")
    public ResponseEntity<List<ProductDto>> searchByNameAndBrand(@RequestBody SearchDto searchDto)  {
        return  new ResponseEntity<>(productService.searchByNameAndBrand(searchDto.getSearch()), HttpStatus.OK);
    }

    @PostMapping("/filterProduct")
    public ResponseEntity<List<ProductDto>> filterProduct(@RequestBody SearchDto searchDto)  {
        return  new ResponseEntity<>(productService.filterProduct(searchDto), HttpStatus.OK);
    }


    @GetMapping("/findByCategory/{pageNo}/{pageSize}/{id}")
    public ResponseEntity<List<ProductDto>> findByCategory(@PathVariable int pageNo, @PathVariable int pageSize,@PathVariable Long id)  {
        try{
            return  new ResponseEntity<>(productService.findByCategory(pageNo,pageSize,id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/sort/{pageNo}/{pageSize}/{sort}/{id}")
    public ResponseEntity<List<ProductDto>> sort(@PathVariable int pageNo, @PathVariable int pageSize,@PathVariable String sort,@PathVariable Long id)  {
        try{
            return  new ResponseEntity<>(productService.sort(pageNo,pageSize,sort,id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long id)  {

            return  new ResponseEntity<>(
                    productService.findById(id), HttpStatus.OK);

    }
}
