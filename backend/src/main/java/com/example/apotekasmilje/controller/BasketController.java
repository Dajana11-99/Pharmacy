package com.example.apotekasmilje.controller;

import com.example.apotekasmilje.dto.BasketDto;

import com.example.apotekasmilje.dto.BasketProductsDto;
import com.example.apotekasmilje.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "/basket", produces = MediaType.APPLICATION_JSON_VALUE)
public class BasketController {

    @Autowired
    private BasketService basketService;

    @PreAuthorize("hasRole('Authenticated_User')")
    @PostMapping("/add")
    public ResponseEntity<Boolean> addProduct(@RequestBody BasketDto basketDto)  {
        return  new ResponseEntity<>(basketService.addProduct(basketDto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('Authenticated_User')")
    @PostMapping("/update")
    public ResponseEntity<Boolean> updateQuantity(@RequestBody BasketProductsDto basketDto)  {
        return  new ResponseEntity<>(basketService.updateQuantity(basketDto), HttpStatus.OK);
    }
}
