package com.example.apotekasmilje.controller;
import com.example.apotekasmilje.dto.ImageDto;
import com.example.apotekasmilje.dto.ProductCategoryDto;
import com.example.apotekasmilje.service.FirebaseService;
import com.example.apotekasmilje.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/firebase", produces = MediaType.APPLICATION_JSON_VALUE)
public class FirebaseController {

    @Autowired
    private FirebaseService firebaseService;
    private final Logger logger= LoggerFactory.getLogger(FirebaseController.class);

    @PostMapping(value="/uploadProductImage/{name}")
    public ResponseEntity<String> uploadCabinImage(@PathVariable("name") String name, @RequestParam("file") MultipartFile multipartFile) {
        try {
          return  new ResponseEntity<>(firebaseService.uploadProductImage(multipartFile,name), HttpStatus.OK);
        } catch (IOException e) {
             logger.error(e.toString());
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping(value="/uploadBlogImage/{name}")
    public ResponseEntity<String> uploadBlogImage(@PathVariable("name") String name, @RequestParam("file") MultipartFile multipartFile) {
        try {
            return  new ResponseEntity<>(firebaseService.uploadBlogImage(multipartFile,name), HttpStatus.OK);
        } catch (IOException e) {
            logger.error(e.toString());
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }

    }
}
