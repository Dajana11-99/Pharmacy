package com.example.apotekasmilje.service.impl;

import com.example.apotekasmilje.dto.EvaluationDto;
import com.example.apotekasmilje.mapper.EvaluationMapper;
import com.example.apotekasmilje.model.products.Product;
import com.example.apotekasmilje.model.products.ProductEvaluation;
import com.example.apotekasmilje.model.users.AuthenticatedUser;
import com.example.apotekasmilje.model.users.Person;
import com.example.apotekasmilje.repository.ProductEvaluationRepository;
import com.example.apotekasmilje.service.PersonService;
import com.example.apotekasmilje.service.ProductEvaluationService;
import com.example.apotekasmilje.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductEvaluationServiceImpl implements ProductEvaluationService {

    @Autowired
    private ProductEvaluationRepository productEvaluationRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private ProductService productService;

    private EvaluationMapper evaluationMapper = new EvaluationMapper();
    public boolean save(EvaluationDto evaluationDto) {
        Person person = personService.findByPersonEmail(evaluationDto.getEmail());
        if (person == null) return false;
        Product product = productService.findProductById(evaluationDto.getProductId());
        if (product == null) return false;
        ProductEvaluation productEvaluation =
                evaluationMapper.evaluationDtoToEvaluation(evaluationDto);
        productEvaluation.setProduct(product);
        productEvaluation.setAuthenticatedUser((AuthenticatedUser) person);
        productEvaluationRepository.save(productEvaluation);
        return true;
    }

    @Override
    public List<EvaluationDto> getAll(int pageNo, int pageSize,Long id) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("date").descending());
        List<ProductEvaluation> pagedResult = productEvaluationRepository.findByProductId(id,paging);
        return evaluationMapper.evaluationsToEvaluationDtos(pagedResult);
    }

    @Override
    public List<EvaluationDto> findAll(int pageNo,int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("date").ascending());
        return  evaluationMapper
                .evaluationsToEvaluationDtos(productEvaluationRepository.findAll(paging).toList());
    }

    public Float evaluationAvgByProductId(Long id){
        return productEvaluationRepository.evaluationAvgByProductId(id);
    }

    @Override
    public boolean update(EvaluationDto evaluationDto) {
        try{
            ProductEvaluation productEvaluation=
                    productEvaluationRepository.findById(evaluationDto.getId()).get();
            if(productEvaluation==null)return false;
            productEvaluation.setStatus(true);
            productEvaluationRepository.save(productEvaluation);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean delete(EvaluationDto evaluationDto) {
        try {
            ProductEvaluation productEvaluation=
                    productEvaluationRepository.findById(evaluationDto.getId()).get();
            if(productEvaluation==null)return false;
            productEvaluationRepository.deleteById(productEvaluation.getId());
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
