package com.example.apotekasmilje.repository;

import com.example.apotekasmilje.model.users.Person;
import com.example.apotekasmilje.model.users.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("SELECT m FROM Person m WHERE  m.personEmail = :email and m.isDeleted=false")
    Person findByPersonEmail(String email);
    @Query("SELECT m.userRole.name FROM Person m WHERE  m.id = :id and m.isDeleted=false")
    String findRoleById(Long id);


}