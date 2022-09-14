package com.example.apotekasmilje.service.impl;

import com.example.apotekasmilje.dto.ComplaintDto;
import com.example.apotekasmilje.mapper.ComplaintMapper;
import com.example.apotekasmilje.model.products.Complaint;
import com.example.apotekasmilje.model.products.ProductSale;
import com.example.apotekasmilje.repository.ComplaintRepository;
import com.example.apotekasmilje.service.ComplaintService;
import com.example.apotekasmilje.service.OrderService;
import com.example.apotekasmilje.service.SaleProductService;
import com.example.apotekasmilje.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;
    @Autowired
    private SaleProductService saleProductService;

    @Autowired
    private OrderService orderService;

    private ComplaintMapper complaintMapper= new ComplaintMapper();

    public Boolean createComplaint(ComplaintDto complaintDto){
        try {
            ProductSale productSale = saleProductService.findByProductAndSale(complaintDto.getProductId(), complaintDto.getSaleId());
            Complaint complaint = complaintMapper.complaintDtoToComplaint(complaintDto);
            complaint.setProductSale(productSale);
            complaintRepository.save(complaint);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<ComplaintDto> findAllUnacceptedComplaints(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        List<Complaint> pagedResult = complaintRepository.findAllUnacceptedComplaints(paging);
        return complaintMapper.complaintsToComplaintDtos(pagedResult);
    }

    public Boolean deleteComplaint(ComplaintDto complaintDto){
        complaintRepository.deleteById(complaintDto.getId());
        return true;
    }

    public Boolean acceptComplaint(ComplaintDto complaintDto){
        ProductSale productSale = saleProductService.findByProductAndSale(complaintDto.getProductId(), complaintDto.getSaleId());
        if(productSale!=null) {
            Complaint complaint = complaintRepository.findById(complaintDto.getId()).get();
            if (orderService.checkDidUserOrderThisProduct(complaintDto.getProductId())) return false;
            complaintRepository.deleteById(complaint.getId());
            saleProductService.deleteProductFromSale(complaintDto.getProductId(), complaintDto.getSaleId());
            return true;
        }else {
            return false;
        }
    }


}
