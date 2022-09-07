package com.example.apotekasmilje.service.impl;

import com.example.apotekasmilje.dto.CharacteristicsDto;
import com.example.apotekasmilje.model.products.Characteristics;
import com.example.apotekasmilje.repository.ProductCharacteristicRepository;
import com.example.apotekasmilje.service.CharacteristicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacteristicsServiceImpl implements CharacteristicsService {
    @Autowired
    private ProductCharacteristicRepository productCharacteristicRepository;

    public void deleteCharacteristics(Long id){
         productCharacteristicRepository
                 .deleteAll(productCharacteristicRepository.findByProductId(id));
    }

}
