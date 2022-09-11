package com.example.apotekasmilje.controller;

import com.example.apotekasmilje.dto.BasketProductsDto;
import com.example.apotekasmilje.dto.PersonDto;
import com.example.apotekasmilje.dto.ProductDto;
import com.example.apotekasmilje.dto.SearchDto;
import com.example.apotekasmilje.service.BasketProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/basketProducts", produces = MediaType.APPLICATION_JSON_VALUE)
public class BasketProductsController {

    @Autowired
    private BasketProductsService basketProductsService;

    @PreAuthorize("hasRole('Authenticated_User')")
    @GetMapping("/products/{id}")
    public ResponseEntity<List<BasketProductsDto>> findAll(@PathVariable Long id)  {
        return  new ResponseEntity<>(basketProductsService.findProductInBasket(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('Authenticated_User')")
    @PostMapping("/delete")
    public ResponseEntity<Boolean> delete(@RequestBody BasketProductsDto basketDto)  {
        return  new ResponseEntity<>(basketProductsService.deleteProduct(basketDto), HttpStatus.OK);
    }

}
