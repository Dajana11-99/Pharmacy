package com.example.apotekasmilje.repository;

import com.example.apotekasmilje.model.users.AuthenticatedUser;
import com.example.apotekasmilje.model.users.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticatedRepository extends JpaRepository<AuthenticatedUser, Long> {
}
