package com.example.apotekasmilje.controller;

import com.example.apotekasmilje.dto.ProductDto;
import com.example.apotekasmilje.dto.RankDto;
import com.example.apotekasmilje.dto.SaleDto;
import com.example.apotekasmilje.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/sale", produces = MediaType.APPLICATION_JSON_VALUE)
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping("/getCurrentSales/{pageNo}/{pageSize}")
    public ResponseEntity<List<SaleDto>> getCurrentSales(@PathVariable int pageNo, @PathVariable int pageSize)  {
        return  new ResponseEntity<>(saleService.getCurrentSales(pageNo,pageSize), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('Pharmacy_Technicians')")
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody SaleDto saleDto)  {

            if(saleService.addSale(saleDto))
                return  new ResponseEntity<>("Успјешно креирана акција!", HttpStatus.OK);
            return  new ResponseEntity<>("Акција са тим именом већ постоји!", HttpStatus.BAD_REQUEST);

    }
}
