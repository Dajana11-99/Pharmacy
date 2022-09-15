package com.example.apotekasmilje.controller;

import com.example.apotekasmilje.dto.BlogDto;
import com.example.apotekasmilje.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/blog", produces = MediaType.APPLICATION_JSON_VALUE)
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/save")
    @PreAuthorize("hasRole('Pharmacy_Technicians')")
    public ResponseEntity<String> save(@RequestBody BlogDto blogDto)  {
        if(blogService.save(blogDto))
            return  new ResponseEntity<>("Успјешно сте креирали блог!", HttpStatus.OK);
        return  new ResponseEntity<>("Блог са тим насловом већ постоји!", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('Master_Of_Pharmacy')")
    public ResponseEntity<String> publish(@RequestBody BlogDto blogDto)  {
        if(blogService.publish(blogDto))
            return  new ResponseEntity<>("Успјешно сте одобрили блог!", HttpStatus.OK);
        return  new ResponseEntity<>("Блог није одобрен!", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('Master_Of_Pharmacy')")
    public ResponseEntity<String> delete(@RequestBody BlogDto blogDto)  {
        if(blogService.delete(blogDto))
            return  new ResponseEntity<>("Успјешно сте обрисали блог!", HttpStatus.OK);
        return  new ResponseEntity<>("Блог није обрисан!", HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('Pharmacy_Technicians')||hasRole('Master_Of_Pharmacy')")
    @GetMapping("/findAllAccepted/{pageNo}/{pageSize}")
    public ResponseEntity<List<BlogDto>> findAllAccepted(@PathVariable int pageNo, @PathVariable int pageSize) {
        return new ResponseEntity<>(blogService.findAllAccepted(pageNo,pageSize), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('Pharmacy_Technicians')")
    @GetMapping("/findByUser/{pageNo}/{pageSize}/{email}/")
    public ResponseEntity<List<BlogDto>> findByUser(@PathVariable int pageNo, @PathVariable int pageSize, @PathVariable String email) {
        return new ResponseEntity<>(blogService.findByUser(pageNo,pageSize,email), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('Master_Of_Pharmacy')")
    @GetMapping("/findAllUnAccepted/{pageNo}/{pageSize}")
    public ResponseEntity<List<BlogDto>> findAllUnAccepted(@PathVariable int pageNo, @PathVariable int pageSize) {
        return new ResponseEntity<>(blogService.findAllUnAccepted(pageNo,pageSize), HttpStatus.OK);
    }

    @GetMapping("/findAllPubishedBlogsByType/{pageNo}/{pageSize}/{type}")
    public ResponseEntity<List<BlogDto>> findAllPubishedBlogsByType(@PathVariable int pageNo, @PathVariable int pageSize,@PathVariable int type) {
        return new ResponseEntity<>(blogService.findAllPubishedBlogsByType(pageNo,pageSize,type), HttpStatus.OK);
    }


    @PostMapping("/findByTitle")
    public ResponseEntity<BlogDto> findByTitle(@RequestBody BlogDto blogDto) {
        return new ResponseEntity<>(blogService.findByTitle(blogDto.getTitle()), HttpStatus.OK);
    }


}
