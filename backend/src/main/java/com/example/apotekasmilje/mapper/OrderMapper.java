package com.example.apotekasmilje.mapper;

import com.example.apotekasmilje.dto.OrderDto;
import com.example.apotekasmilje.dto.OrderInformationDto;
import com.example.apotekasmilje.dto.PaymentDto;
import com.example.apotekasmilje.model.order.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    private PaymentMapper paymentMapper = new PaymentMapper();
    private DeliveryMapper deliveryMapper= new DeliveryMapper();
    private ProductOrderMapper productOrderMapper = new ProductOrderMapper();
    public Order  orderDtoToOrder(OrderDto orderDto){
        return new Order(orderDto.getId(), LocalDate.now(),orderDto.getTotalPrice(),orderDto.getStatus(),orderDto.getDiscountPercentage()
        ,null,null,null,null);
    }

  public OrderInformationDto orderToOrderInformation(Order order){
      return   new OrderInformationDto(order.getId(),order.getDate().toString(),order.getTotalPrice(),order.getStatus(),
           paymentMapper.paymentToPaymentDto(order.getPayment()),deliveryMapper.deliveryToDeliveryDto(order.getDelivery()),
                productOrderMapper.orderProductsToOrderProductDtos(order.getOrderProducts()),order.getAuthenticatedUser().getFirstName()+"  "+order.getAuthenticatedUser().getLastName(),order.getAuthenticatedUser().getPhoneNum()
        ,order.getAuthenticatedUser().getAddress(),order.getAuthenticatedUser().getZipCode().toString(),order.getAuthenticatedUser().getPlace(),order.getDiscountPercentage());
  }
    public  List<OrderInformationDto>ordersToOrderDtos(List<Order> orders){
        List<OrderInformationDto>orderDtos = new ArrayList<>();
        for(Order order:orders)
            orderDtos.add(orderToOrderInformation(order));
        return orderDtos;
    }
}
