package com.example.apotekasmilje.repository;


import com.example.apotekasmilje.model.products.Complaint;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ComplaintRepository extends PagingAndSortingRepository<Complaint, Long> {
    @Query("SELECT m FROM Complaint m  WHERE m.status = false")
    List<Complaint> findAllUnacceptedComplaints(Pageable paging);
}
