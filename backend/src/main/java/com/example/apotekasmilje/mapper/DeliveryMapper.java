package com.example.apotekasmilje.mapper;

import com.example.apotekasmilje.dto.DeliveryDto;
import com.example.apotekasmilje.model.order.Delivery;

import java.util.ArrayList;
import java.util.List;

public class DeliveryMapper {

    public DeliveryDto deliveryToDeliveryDto(Delivery delivery){
        return  new DeliveryDto(delivery.getId(), delivery.getName(), delivery.getPrice());
    }

    public List<DeliveryDto> deliveryToDeliveryDtos(List<Delivery> deliveries){
        List<DeliveryDto> deliveryDtos = new ArrayList<>();
        for(Delivery delivery: deliveries)
            deliveryDtos.add(deliveryToDeliveryDto(delivery));
        return deliveryDtos;
    }
}
