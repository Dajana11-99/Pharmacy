package com.example.apotekasmilje.service;

import com.example.apotekasmilje.dto.ComplaintDto;

import java.util.List;

public interface ComplaintService {
     Boolean createComplaint(ComplaintDto complaintDto);

     List<ComplaintDto> findAllUnacceptedComplaints(int pageNo, int pageSize);
     Boolean deleteComplaint(ComplaintDto complaintDto);
     Boolean acceptComplaint(ComplaintDto complaintDto);
}
