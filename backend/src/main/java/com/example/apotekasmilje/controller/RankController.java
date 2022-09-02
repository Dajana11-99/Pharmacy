package com.example.apotekasmilje.controller;



import com.example.apotekasmilje.dto.RankDto;
import com.example.apotekasmilje.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
