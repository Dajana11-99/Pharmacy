package com.example.apotekasmilje.controller;

import com.example.apotekasmilje.dto.ComplaintDto;
import com.example.apotekasmilje.dto.OrderDto;
import com.example.apotekasmilje.dto.OrderInformationDto;
import com.example.apotekasmilje.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/complaint", produces = MediaType.APPLICATION_JSON_VALUE)
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;
    @PostMapping("/save")
    @PreAuthorize("hasRole('Pharmacy_Technicians')")
    public ResponseEntity<String> save(@RequestBody ComplaintDto complaintDto)  {
        if(complaintService.createComplaint(complaintDto))
            return  new ResponseEntity<>("Успјешно сте креирали жалбу!", HttpStatus.OK);
        return  new ResponseEntity<>("Жалба није креирана!", HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('Master_Of_Pharmacy')")
    @GetMapping("/findAllUnacceptedComplaints/{pageNo}/{pageSize}")
    public ResponseEntity<List<ComplaintDto>> findAllUnacceptedComplaints(@PathVariable int pageNo, @PathVariable int pageSize) {
        return new ResponseEntity<>(complaintService.findAllUnacceptedComplaints(pageNo,pageSize), HttpStatus.OK);
    }
    @PostMapping("/delete")
    @PreAuthorize("hasRole('Master_Of_Pharmacy')")
    public ResponseEntity<String> delete(@RequestBody ComplaintDto complaintDto)  {
        if(complaintService.deleteComplaint(complaintDto))
            return  new ResponseEntity<>("Успјешно сте обрисали жалбу!", HttpStatus.OK);
        return  new ResponseEntity<>("Жалба није обрисана!", HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/acceptComplaint")
    @PreAuthorize("hasRole('Master_Of_Pharmacy')")
    public ResponseEntity<String> acceptComplaint(@RequestBody ComplaintDto complaintDto)  {
        if(complaintService.acceptComplaint(complaintDto))
            return  new ResponseEntity<>("Успјешно сте искључили производ са акције!", HttpStatus.OK);
        return  new ResponseEntity<>("Корисник је поручио овај производ не може се обрисати!", HttpStatus.BAD_REQUEST);
    }

}
