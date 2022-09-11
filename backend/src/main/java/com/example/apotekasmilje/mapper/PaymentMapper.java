package com.example.apotekasmilje.mapper;

import com.example.apotekasmilje.dto.DeliveryDto;
import com.example.apotekasmilje.dto.PaymentDto;
import com.example.apotekasmilje.model.order.Delivery;
import com.example.apotekasmilje.model.order.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentMapper {
    public PaymentDto paymentToPaymentDto(Payment payment){
        return  new PaymentDto(payment.getId(), payment.getName(), payment.getPrice());
    }
    public List<PaymentDto> paymentToPaymentDtos(List<Payment> payments){
        List<PaymentDto> deliveryDtos = new ArrayList<>();
        for(Payment payment: payments)
            deliveryDtos.add(paymentToPaymentDto(payment));
        return deliveryDtos;
    }
}
