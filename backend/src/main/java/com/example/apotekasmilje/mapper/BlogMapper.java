package com.example.apotekasmilje.mapper;

import com.example.apotekasmilje.dto.BlogDto;
import com.example.apotekasmilje.model.blog.Blog;

import java.util.ArrayList;
import java.util.List;

public class BlogMapper {
    private ImageMapper imageMapper = new ImageMapper();
    public Blog blogDtoToBlog(BlogDto blogDto){
        return new Blog(blogDto.getId(), blogDto.getText(), blogDto.getTitle(),false, blogDto.getDescription(), blogDto.getType(),
        null,null);
    }
    public BlogDto blogToBlogDto(Blog blog){
        return new BlogDto(blog.getId(), blog.getText(), blog.getTitle(), blog.getDescription(), blog.isStatus(), blog.getType()
        ,imageMapper.imagesToImageDtos(blog.getImage()),blog.getPharmacyTechnicians().getFirstName()
                +"  "+blog.getPharmacyTechnicians().getLastName(),blog.getPharmacyTechnicians().getPersonEmail());
    }

    public List<BlogDto> blogsToBlogDtos(List<Blog> blogs){
        List<BlogDto>blogDtos= new ArrayList<>();
        for(Blog blog:blogs)
            blogDtos.add(blogToBlogDto(blog));
        return blogDtos;
    }
}
