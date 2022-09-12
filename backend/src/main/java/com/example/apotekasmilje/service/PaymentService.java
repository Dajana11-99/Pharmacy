package com.example.apotekasmilje.service;

import com.example.apotekasmilje.dto.PaymentDto;
import com.example.apotekasmilje.model.order.Payment;

import java.util.List;

public interface PaymentService {
    List<PaymentDto> getAll();
    Payment findById(Long id);
}
