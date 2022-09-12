package com.example.apotekasmilje.controller;


import com.example.apotekasmilje.dto.OrderDto;
import com.example.apotekasmilje.dto.OrderInformationDto;
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

    @PreAuthorize("hasRole('Authenticated_User')")
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody OrderDto orderDto)  {

                if(orderService.saveOrder(orderDto))
                    return  new ResponseEntity<>("Успјешно сте креирали поруџбину!", HttpStatus.OK);
                return  new ResponseEntity<>("Поруџбина није креирана!", HttpStatus.BAD_REQUEST);

    }

    @PreAuthorize("hasRole('Authenticated_User')")
    @PostMapping("/cancelOrder")
    public ResponseEntity<String> canellation(@RequestBody OrderDto orderDto)  {
        try {
            if(orderService.cancelOrder(orderDto))
                return  new ResponseEntity<>("Успјешно сте отказали поруџбину!", HttpStatus.OK);
            return  new ResponseEntity<>("Поруџбина није отказана!", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return  new ResponseEntity<>("Техничка грешка, покушајте поново касније", HttpStatus.BAD_REQUEST);
        }
    }

}
