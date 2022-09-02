package com.example.apotekasmilje.repository;

import com.example.apotekasmilje.model.users.Person;
import com.example.apotekasmilje.model.users.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    @Query("SELECT m FROM UserRole m WHERE  m.name = :name and m.isDeleted=false")
    UserRole findByName(String name);
}
