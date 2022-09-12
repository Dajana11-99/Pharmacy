package com.example.apotekasmilje.service.impl;

import com.example.apotekasmilje.dto.DeliveryDto;
import com.example.apotekasmilje.mapper.DeliveryMapper;
import com.example.apotekasmilje.model.order.Delivery;
import com.example.apotekasmilje.repository.DeliveryRepository;
import com.example.apotekasmilje.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    private DeliveryMapper deliveryMapper= new DeliveryMapper();

    public List<DeliveryDto> getAll(){
        return deliveryMapper.deliveryToDeliveryDtos(deliveryRepository.findAll());
    }

    @Override
    public Delivery findById(Long id) {
        return  deliveryRepository.findById(id).get();
    }
}
