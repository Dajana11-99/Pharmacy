package com.example.apotekasmilje.controller;

import com.example.apotekasmilje.dto.DeliveryDto;
import com.example.apotekasmilje.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/delivery", produces = MediaType.APPLICATION_JSON_VALUE)
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;
    @PreAuthorize("hasRole('Authenticated_User')")
    @GetMapping("/all")
    public ResponseEntity<List<DeliveryDto>> findAll() {
        return new ResponseEntity<>(deliveryService.getAll(), HttpStatus.OK);
    }
}
