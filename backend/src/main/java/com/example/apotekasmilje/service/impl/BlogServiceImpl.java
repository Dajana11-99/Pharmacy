package com.example.apotekasmilje.service.impl;

import com.example.apotekasmilje.dto.BlogDto;
import com.example.apotekasmilje.dto.ComplaintDto;
import com.example.apotekasmilje.mapper.BlogMapper;
import com.example.apotekasmilje.mapper.PersonMapper;
import com.example.apotekasmilje.model.blog.Blog;
import com.example.apotekasmilje.model.products.Complaint;
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
