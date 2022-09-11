package com.example.apotekasmilje.repository;


import com.example.apotekasmilje.model.order.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
