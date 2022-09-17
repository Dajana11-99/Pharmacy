package com.example.apotekasmilje.service;

import com.example.apotekasmilje.dto.OrderDto;
import com.example.apotekasmilje.dto.OrderInformationDto;

import java.util.List;

public interface OrderService {
    boolean saveOrder(OrderDto orderDto);
    List<OrderInformationDto> findAllByUser(String email);
    public Boolean cancelOrder(OrderDto orderDto);

    List<OrderInformationDto> getUserHistory(int pageNo,int pageSize,String email);
    List<OrderInformationDto> findAllCurrentOrders(int pageNo,int pageSize);
    List<OrderInformationDto> findAllAcceptedOrders(int pageNo,int pageSize);

    List<OrderInformationDto> findAllDeliveredOrders(int pageNo,int pageSize);

    Boolean changeOrderStatus(String status, Long id);

    boolean checkDidUserOrderThisProduct(Long productId);
    List<OrderInformationDto>searchByUser(String name);
    List<OrderInformationDto>searchByStatus(String status);
}
