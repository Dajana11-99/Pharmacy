package com.example.apotekasmilje.mapper;

import com.example.apotekasmilje.dto.ComplaintDto;
import com.example.apotekasmilje.model.products.Complaint;

import java.util.ArrayList;
import java.util.List;

public class ComplaintMapper {

    private SaleProductsMapper saleProductsMapper = new SaleProductsMapper();

    public Complaint complaintDtoToComplaint(ComplaintDto complaintDto){
        return new Complaint(complaintDto.getId(),complaintDto.getReason(),false, null);
    }

    public ComplaintDto complaintToComplaintDto(Complaint complaint){
        return new ComplaintDto(complaint.getId(),complaint.getReason(),
                complaint.isStatus(),complaint.getProductSale().getProduct().getId(),complaint.getProductSale().getSale().getId()
        ,complaint.getProductSale().getProduct().getName(),complaint.getProductSale().getSale().getName()) ;
    }

   public List<ComplaintDto> complaintsToComplaintDtos(List<Complaint> complaints){
        List<ComplaintDto> complaintDtos= new ArrayList<>();
        for(Complaint complaint: complaints)
            complaintDtos.add(complaintToComplaintDto(complaint));
        return complaintDtos;
    }
}
