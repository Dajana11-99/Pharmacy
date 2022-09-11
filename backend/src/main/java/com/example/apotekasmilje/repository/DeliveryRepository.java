package com.example.apotekasmilje.repository;

import com.example.apotekasmilje.model.order.Delivery;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
