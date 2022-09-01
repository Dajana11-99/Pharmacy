package com.example.apotekasmilje.repository;

import com.example.apotekasmilje.model.users.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findByPersonEmail(String email);
    @Query("SELECT m.userRole FROM Person m WHERE  m.id = :id and m.isDeleted=false")
    String findRoleById(Integer id);
}
