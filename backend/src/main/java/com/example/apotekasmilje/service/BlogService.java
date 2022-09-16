package com.example.apotekasmilje.service;

import com.example.apotekasmilje.dto.BlogDto;
import com.example.apotekasmilje.model.blog.Blog;
import com.example.apotekasmilje.model.products.Image;

import java.util.List;

public interface BlogService {
    void addNewImage(String name, Image image);
    Boolean save(BlogDto blogDto);
    List<BlogDto> findByUser(int pageNo, int pageSize, String email);

    List<BlogDto> findAllUnAccepted(int pageNo, int pageSize);
    List<BlogDto> findAllPubishedBlogsByType(int pageNo, int pageSize,int type);

    BlogDto findByTitle(String title);

    boolean publish(BlogDto blogDto);

    boolean delete(BlogDto blogDto);

    List<BlogDto> findAllAccepted(int pageNo, int pageSize);

    Blog findById(Long blogId);
}
