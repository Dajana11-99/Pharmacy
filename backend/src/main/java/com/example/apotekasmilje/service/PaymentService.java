package com.example.apotekasmilje.service;

import com.example.apotekasmilje.dto.PaymentDto;

import java.util.List;

public interface PaymentService {
    List<PaymentDto> getAll();
}
