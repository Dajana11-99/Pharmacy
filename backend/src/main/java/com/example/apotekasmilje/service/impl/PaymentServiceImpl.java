package com.example.apotekasmilje.service.impl;

import com.example.apotekasmilje.dto.PaymentDto;
import com.example.apotekasmilje.mapper.PaymentMapper;
import com.example.apotekasmilje.model.order.Payment;
import com.example.apotekasmilje.repository.PaymentRepository;
import com.example.apotekasmilje.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    private PaymentMapper paymentMapper = new PaymentMapper();

    public List<PaymentDto> getAll(){
        return paymentMapper.paymentToPaymentDtos(paymentRepository.findAll());
    }

    public Payment findById(Long id){
        return paymentRepository.findById(id).get();
    }
}
