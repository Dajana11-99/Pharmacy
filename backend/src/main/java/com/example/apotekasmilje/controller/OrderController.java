package com.example.apotekasmilje.controller;


import com.example.apotekasmilje.dto.OrderDto;
import com.example.apotekasmilje.dto.OrderInformationDto;
import com.example.apotekasmilje.dto.SearchDto;
import com.example.apotekasmilje.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasRole('Authenticated_User')")
    @GetMapping("/allByUser/{email}/")
    public ResponseEntity<List<OrderInformationDto>> allByUser(@PathVariable String email) {
        return new ResponseEntity<>(orderService.findAllByUser(email), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('Authenticated_User')")
    @GetMapping("/orderHistoryByUser/{pageNo}/{pageSize}/{email}/")
    public ResponseEntity<List<OrderInformationDto>> getUserHistory(@PathVariable int pageNo,@PathVariable int pageSize,@PathVariable String email) {
        return new ResponseEntity<>(orderService.getUserHistory(pageNo,pageSize,email), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('Pharmacy_Technicians')")
    @GetMapping("/findAllCurrentOrders/{pageNo}/{pageSize}")
    public ResponseEntity<List<OrderInformationDto>> findAllCurrentOrders(@PathVariable int pageNo,@PathVariable int pageSize) {
        return new ResponseEntity<>(orderService.findAllCurrentOrders(pageNo,pageSize), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('Pharmacy_Technicians')")
    @GetMapping("/findAllAcceptedOrders/{pageNo}/{pageSize}")
    public ResponseEntity<List<OrderInformationDto>> findAllAcceptedOrders(@PathVariable int pageNo,@PathVariable int pageSize) {
        return new ResponseEntity<>(orderService.findAllAcceptedOrders(pageNo,pageSize), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('Pharmacy_Technicians')")
    @GetMapping("/findAllDeliveredOrders/{pageNo}/{pageSize}")
    public ResponseEntity<List<OrderInformationDto>> findAllDeliveredOrders(@PathVariable int pageNo,@PathVariable int pageSize) {
        return new ResponseEntity<>(orderService.findAllDeliveredOrders(pageNo,pageSize), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('Pharmacy_Technicians')")
    @PostMapping("/searchByName")
    public ResponseEntity<List<OrderInformationDto>> searchByName(@RequestBody SearchDto searchDto) {
        return new ResponseEntity<>(orderService.searchByUser(searchDto.getSearch()), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('Pharmacy_Technicians')")
    @GetMapping("/searchByStatus/{status}")
    public ResponseEntity<List<OrderInformationDto>> searchByStatus(@PathVariable String status) {
        return new ResponseEntity<>(orderService.searchByStatus(status), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('Pharmacy_Technicians')")
    @GetMapping("/changeOrderStatus/{status}/{id}")
    public ResponseEntity<Boolean> changeOrderStatus(@PathVariable String status,@PathVariable Long id) {
        if(orderService.changeOrderStatus(status,id))
            return new ResponseEntity<>(true, HttpStatus.OK);
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('Authenticated_User')")
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody OrderDto orderDto)  {

                if(orderService.saveOrder(orderDto))
                    return  new ResponseEntity<>("???????????????? ?????? ???????????????? ??????????????????!", HttpStatus.OK);
                return  new ResponseEntity<>("?????????????????? ???????? ????????????????!", HttpStatus.BAD_REQUEST);

    }

    @PreAuthorize("hasRole('Authenticated_User')")
    @PostMapping("/cancelOrder")
    public ResponseEntity<String> canellation(@RequestBody OrderDto orderDto)  {
        try {
            if(orderService.cancelOrder(orderDto))
                return  new ResponseEntity<>("???????????????? ?????? ???????????????? ??????????????????!", HttpStatus.OK);
            return  new ResponseEntity<>("?????????????????? ???????? ????????????????!", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return  new ResponseEntity<>("???????????????? ????????????, ?????????????????? ???????????? ??????????????", HttpStatus.BAD_REQUEST);
        }
    }

}
