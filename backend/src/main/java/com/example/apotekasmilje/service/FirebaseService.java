package com.example.apotekasmilje.service;

import com.example.apotekasmilje.model.products.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface FirebaseService {
    String uploadProductImage(MultipartFile newImage, String name) throws IOException;

}
