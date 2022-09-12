package com.example.apotekasmilje.service.impl;

import com.example.apotekasmilje.dto.OrderDto;
import com.example.apotekasmilje.dto.OrderInformationDto;
import com.example.apotekasmilje.dto.OrderProductsDto;
import com.example.apotekasmilje.mapper.OrderMapper;
import com.example.apotekasmilje.model.enums.OrderStatus;
import com.example.apotekasmilje.model.order.*;
import com.example.apotekasmilje.model.products.Product;
import com.example.apotekasmilje.model.users.AuthenticatedUser;
import com.example.apotekasmilje.repository.OrderRepository;
import com.example.apotekasmilje.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private DeliveryService deliveryService;

    private OrderMapper orderMapper = new OrderMapper();

    @Autowired
    private PersonService personService;
    @Autowired
    private BasketProductsService basketProductsService;
    @Autowired
    private BasketService basketService;

    public boolean saveOrder(OrderDto orderDto){
            Payment payment= paymentService.findById(orderDto.getPaymentId());
            Delivery delivery = deliveryService.findById(orderDto.getDeliveryId());
            AuthenticatedUser person = (AuthenticatedUser) personService.findByPersonEmail(orderDto.getUserEmail());
            personService.setUserPoints(person);
                 Order order= orderMapper.orderDtoToOrder(orderDto);
                order.setAuthenticatedUser(person);
                order.setPayment(payment);
                order.setDelivery(delivery);
                order.setStatus(OrderStatus.CREATED);
                 Order saved=orderRepository.save(order);
                setProductInOrder(orderDto,saved);
            basketProductsService.clearBasket(basketService.findByUserId(person.getId()).getId());
            return true;


    }
    private void setProductInOrder(OrderDto orderDto,Order order){
        List<OrderProducts> orderProducts = new ArrayList<>();
        for(OrderProductsDto or: orderDto.getProduct()) {
            OrderProductId id = new OrderProductId(order.getId(), or.getProduct().getId());
            orderProducts.add(new OrderProducts(id,null,null,or.getQuantity()));
        }
        order.setOrderProducts(orderProducts);
        orderRepository.save(order);

    }

    public List<OrderInformationDto> findAllByUser(String email){
       return orderMapper.ordersToOrderDtos(orderRepository.findAllByUser(email));
    }

    public Boolean cancelOrder(OrderDto orderDto){
        try {
            Order order = orderRepository.findById(orderDto.getId()).get();
            order.setStatus(OrderStatus.CANCELLED);
            personService.updatePoints(order.getAuthenticatedUser());
            orderRepository.save(order);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<OrderInformationDto> getUserHistory(int pageNo,int pageSize,String email) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("date").descending());
        Page<Order> pagedResult = orderRepository.findHistory(email,paging);
        return orderMapper.ordersToOrderDtos(pagedResult.toList());
    }
}
