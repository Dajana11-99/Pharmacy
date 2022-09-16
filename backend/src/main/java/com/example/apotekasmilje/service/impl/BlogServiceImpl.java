package com.example.apotekasmilje.service.impl;

import com.example.apotekasmilje.dto.BlogDto;
import com.example.apotekasmilje.mapper.BlogMapper;
import com.example.apotekasmilje.mapper.PersonMapper;
import com.example.apotekasmilje.model.blog.Blog;
import com.example.apotekasmilje.model.enums.Type;
import com.example.apotekasmilje.model.products.Image;
import com.example.apotekasmilje.model.users.Person;
import com.example.apotekasmilje.model.users.PharmacyTechnicians;
import com.example.apotekasmilje.repository.BlogRepository;
import com.example.apotekasmilje.service.BlogService;
import com.example.apotekasmilje.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private PersonService personService;

    private PersonMapper personMapper = new PersonMapper();

    private BlogMapper blogMapper = new BlogMapper();
    @Override
    public void addNewImage(String name, Image image) {
        Blog blog = blogRepository.findByTitle(name);
        List<Image> currentImages=blog.getImage();
        currentImages.add(image);
        blogRepository.save(blog);
    }
    public List<BlogDto> findByUser(int pageNo, int pageSize,String email) {
        Person person=personService.findByPersonEmail(email);
        Pageable paging = PageRequest.of(pageNo, pageSize);
        List<Blog> pagedResult = blogRepository.findByUser(person.getId(),paging);
        return blogMapper.blogsToBlogDtos(pagedResult);
    }

    @Override
    public List<BlogDto> findAllUnAccepted(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        List<Blog> pagedResult = blogRepository.findAllUnAccepted(paging);
        return blogMapper.blogsToBlogDtos(pagedResult);
    }

    @Override
    public List<BlogDto> findAllPubishedBlogsByType(int pageNo, int pageSize,int type) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Type res;
        if(type==0){
            res =Type.HEALTH;
        }else if(type==1){
            res=Type.BEAUTY;
        }else {
            res=Type.BABIES;
        }
        List<Blog> pagedResult = blogRepository.findAllPubishedBlogsByType(res,paging);
        return blogMapper.blogsToBlogDtos(pagedResult);
    }

    @Override
    public BlogDto findByTitle(String title) {
        return blogMapper.blogToBlogDto(blogRepository.findByTitle(title));
    }

    @Override
    public boolean publish(BlogDto blogDto) {
        try {
            Blog blog =blogRepository.findById(blogDto.getId()).get();
            if(blog==null)return false;
            blog.setStatus(true);
            blogRepository.save(blog);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean delete(BlogDto blogDto) {
        try {
            Blog blog =blogRepository.findById(blogDto.getId()).get();
            if(blog==null)return false;
            blogRepository.deleteById(blog.getId());
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public List<BlogDto> findAllAccepted(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        List<Blog> pagedResult = blogRepository.findAllAccepted(paging);
        return blogMapper.blogsToBlogDtos(pagedResult);
    }

    @Override
    public Blog findById(Long blogId) {
        return blogRepository.findById(blogId).get();
    }

    public Boolean save(BlogDto blogDto){
        try {
            Blog blog =blogRepository.findByTitle(blogDto.getTitle());
            if(blog!=null)return false;
            Person person= personService.findByPersonEmail(blogDto.getPharmacyTechnicians());
            PharmacyTechnicians pharmacyTechnicians =personMapper.convert(person);
            Blog newBlog = blogMapper.blogDtoToBlog(blogDto);
            newBlog.setPharmacyTechnicians(pharmacyTechnicians);
            blogRepository.save(newBlog);
            return true;
        }catch (Exception e){
            return false;
        }

    }
}
