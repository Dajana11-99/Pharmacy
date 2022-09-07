package com.example.apotekasmilje.controller;



import com.example.apotekasmilje.dto.ProductDto;
import com.example.apotekasmilje.dto.RankDto;
import com.example.apotekasmilje.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rank", produces = MediaType.APPLICATION_JSON_VALUE)
public class RankController {

    @Autowired
    private RankService rankService;

    @PreAuthorize("hasRole('Authenticated_User')")
    @PostMapping("/findByUserId/{id}")
    public ResponseEntity<RankDto> findByUserId(@PathVariable Long id)  {
           try {
               RankDto rank=rankService.findByUserId(id);
               return  new ResponseEntity<>(rank, HttpStatus.OK);
           }catch (Exception e){
               return  new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
           }
    }
    @PreAuthorize("hasRole('Master_Of_Pharmacy')")
    @GetMapping("/all/{pageNo}/{pageSize}")
    public ResponseEntity<List<RankDto>> all(@PathVariable int pageNo, @PathVariable int pageSize)  {
        return  new ResponseEntity<>(rankService.getAll(pageNo,pageSize), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('Master_Of_Pharmacy')")
    @PostMapping("/add")
    public ResponseEntity<String> addRank(@RequestBody RankDto rankDto)  {
        try {
            if(rankService.add(rankDto))
                return  new ResponseEntity<>("Успјешно креиран ранг!", HttpStatus.OK);
            return  new ResponseEntity<>("Ранг са тим именом већ постоји!", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return  new ResponseEntity<>("Техничка грешка, покушајте поново касније", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('Master_Of_Pharmacy')")
    @PostMapping("/update")
    public ResponseEntity<String> updateRank(@RequestBody RankDto rankDto)  {
        try {
            if(rankService.update(rankDto))
                return  new ResponseEntity<>("Успјешно ажуриран ранг!", HttpStatus.OK);
            return  new ResponseEntity<>("Ранг није ажуриран!", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return  new ResponseEntity<>("Техничка грешка, покушајте поново касније", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('Master_Of_Pharmacy')")
    @PostMapping("/delete")
    public ResponseEntity<String> deleteRank(@RequestBody RankDto rankDto)  {
        try {
            if(rankService.delete(rankDto))
                return  new ResponseEntity<>("Успјешно сте обрисали ранг!", HttpStatus.OK);
            return  new ResponseEntity<>("Овај ранг је повезан са корисником не може да се обрише!", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return  new ResponseEntity<>("Техничка грешка, покушајте поново касније", HttpStatus.BAD_REQUEST);
        }
    }
}
