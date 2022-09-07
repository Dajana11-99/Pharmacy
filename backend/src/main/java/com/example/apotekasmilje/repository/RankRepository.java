package com.example.apotekasmilje.repository;

import com.example.apotekasmilje.model.users.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RankRepository  extends JpaRepository<Rank, Long> {
    @Query("SELECT m FROM Rank m WHERE  m.name = :name")
    Rank findByName(String name);

    @Query("SELECT m FROM Rank m JOIN AuthenticatedUser a on a.rank.id=m.id  WHERE a.id =:id")
    Rank findRanByUser(@Param("id")Long id);

    List<Rank> findByOrderByPointsAsc();


}
