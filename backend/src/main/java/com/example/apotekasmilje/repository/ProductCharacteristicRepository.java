package com.example.apotekasmilje.repository;


import com.example.apotekasmilje.model.products.Characteristics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductCharacteristicRepository extends JpaRepository<Characteristics, Long> {

}
