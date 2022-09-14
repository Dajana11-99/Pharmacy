package com.example.apotekasmilje.service.impl;

import com.example.apotekasmilje.dto.OrderDto;
import com.example.apotekasmilje.dto.OrderInformationDto;
import com.example.apotekasmilje.dto.OrderProductsDto;
import com.example.apotekasmilje.dto.ProductDto;
import com.example.apotekasmilje.mapper.OrderMapper;
import com.example.apotekasmilje.mapper.ProductMapper;
import com.example.apotekasmilje.mapper.ProductOrderMapper;
import com.example.apotekasmilje.model.enums.OrderStatus;
import com.example.apotekasmilje.model.order.*;
import com.example.apotekasmilje.model.products.Product;
import com.example.apotekasmilje.model.products.ProductSale;
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
    private SaleProductService saleProductService;
    @Autowired
    private BasketService basketService;

    private ProductMapper productMapper = new ProductMapper();
    private ProductOrderMapper productOrderMapper = new ProductOrderMapper();

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
        List<OrderInformationDto> orderInformationDtos= new ArrayList<>();
        for(Order order: orderRepository.findAllByUser(email)){
        orderInformationDtos.add(checkOrderProducts(order));
        }
        return orderInformationDtos;

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
        List<OrderInformationDto> orderInformationDtos= new ArrayList<>();
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("date").descending());
        for(Order order: orderRepository.findHistory(email,paging))
            orderInformationDtos.add(checkOrderProducts(order));
        return orderInformationDtos;
    }

    @Override
    public List<OrderInformationDto> findAllCurrentOrders(int pageNo,int pageSize) {
        List<OrderInformationDto> orderInformationDtos= new ArrayList<>();
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("status").descending());
        for(Order order: orderRepository.findAllCurrentOrders(paging))
            orderInformationDtos.add(checkOrderProducts(order));
        return orderInformationDtos;
    }

    @Override
    public List<OrderInformationDto> findAllAcceptedOrders(int pageNo,int pageSize) {
        List<OrderInformationDto> orderInformationDtos= new ArrayList<>();
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("status").descending());
        for(Order order: orderRepository.findAllAcceptedOrders(paging))
            orderInformationDtos.add(checkOrderProducts(order));
        return orderInformationDtos;
    }

    @Override
    public List<OrderInformationDto> findAllDeliveredOrders(int pageNo,int pageSize) {
        List<OrderInformationDto> orderInformationDtos= new ArrayList<>();
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("status").descending());
        for(Order order: orderRepository.findAllDeliveredOrders(paging))
         orderInformationDtos.add(checkOrderProducts(order));
        return orderInformationDtos;
    }

    @Override
    public Boolean changeOrderStatus(String status, Long id) {
        try {
            Order order = orderRepository.findById(id).get();
            OrderStatus orderStatus=getOrderStatus(status);
            order.setStatus(getOrderStatus(status));
            orderRepository.save(order);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean checkDidUserOrderThisProduct(Long productId) {
        if(orderRepository.checkDidUserOrderProduct(productId).size()>0)
            return true;
        return false;
    }

    private OrderStatus getOrderStatus(String status) {
         if(status.equals("CREATED")){
            return OrderStatus.ACCEPTED;
         } else if(status.equals("ACCEPTED")){
            return OrderStatus.IN_PREPARATION;
        }else if(status.equals("IN_PREPARATION")){
            return OrderStatus.IN_TRANSPORT;
        }else if(status.equals("IN_TRANSPORT")){
            return OrderStatus.DELIVERED;
        }else {
            return OrderStatus.CREATED;
       }
    }
    private  OrderInformationDto checkOrderProducts(Order order){
        List<OrderProductsDto> orderProductsDtos= new ArrayList<>();
        for(OrderProducts p: order.getOrderProducts()) {
            ProductSale productSale = saleProductService.checkDoesProductOnSale(p.getProduct().getId());
            int discount=0;
            if (productSale != null) discount=productSale.getDiscount();
            orderProductsDtos.add(new OrderProductsDto(productMapper.productToProductDt(p.getProduct(),discount),p.getQuantity()));
        }
        return orderMapper.orderToOrderInformation(order, orderProductsDtos);
    }

}
