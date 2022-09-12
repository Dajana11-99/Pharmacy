package com.example.apotekasmilje.service;

import com.example.apotekasmilje.dto.DeliveryDto;
import com.example.apotekasmilje.model.order.Delivery;

import java.util.List;

public interface DeliveryService {
    List<DeliveryDto> getAll();
    Delivery findById(Long id);
}
