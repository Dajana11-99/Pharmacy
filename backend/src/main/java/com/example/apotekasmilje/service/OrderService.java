package com.example.apotekasmilje.service;

import com.example.apotekasmilje.dto.OrderDto;
import com.example.apotekasmilje.dto.OrderInformationDto;

import java.util.List;

public interface OrderService {
    boolean saveOrder(OrderDto orderDto);
    List<OrderInformationDto> findAllByUser(String email);
    public Boolean cancelOrder(OrderDto orderDto);

    List<OrderInformationDto> getUserHistory(int pageNo,int pageSize,String email);
}
