package com.example.apotekasmilje.repository;

import com.example.apotekasmilje.model.products.ProductCategory;
import com.example.apotekasmilje.model.users.AuthenticatedUser;
import com.example.apotekasmilje.model.users.Person;
import com.example.apotekasmilje.model.users.PharmacyTechnicians;
import com.example.apotekasmilje.model.users.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
    @Query("SELECT m FROM Person m WHERE  m.personEmail = :email")
    Person findByPersonEmail(String email);
    @Query("SELECT m FROM PharmacyTechnicians  m WHERE  m.personEmail = :email")
    PharmacyTechnicians findByEmail(@Param("email")String email);
    @Query("SELECT m.userRole.name FROM Person m WHERE  m.id = :id")
    String findRoleById(Long id);
    @Query("SELECT  CASE WHEN  COUNT(m) > 0 THEN true ELSE false END FROM AuthenticatedUser m join Rank  r on m.rank.id=:id")
    Boolean checkIfUserHasRank(@Param("id")Long id);
    @Query("SELECT m FROM AuthenticatedUser m")
    List<AuthenticatedUser> findAllUsers();

    @Query("SELECT m FROM Person m" +
            "  WHERE   UPPER(m.firstName)  LIKE  UPPER(CONCAT(:name, '%')) " +
            " or UPPER(m.personEmail) LIKE UPPER(CONCAT(:name, '%'))" +
            " or UPPER(m.lastName) LIKE UPPER(CONCAT(:name, '%'))")
    List<Person> searchByFirstAndLastNameAndPersonEmail(@Param("name")String name);

}
