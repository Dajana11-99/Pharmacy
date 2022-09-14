package com.example.apotekasmilje.service;

import com.example.apotekasmilje.dto.BlogDto;
import com.example.apotekasmilje.model.products.Image;

import java.util.List;

public interface BlogService {
    void addNewImage(String name, Image image);
    Boolean save(BlogDto blogDto);
    List<BlogDto> findByUser(int pageNo, int pageSize, String email);
}
