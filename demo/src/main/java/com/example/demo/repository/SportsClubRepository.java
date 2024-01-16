package com.example.demo.repository;

import com.example.demo.model.SportsClub;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SportsClubRepository extends CrudRepository<SportsClub, Integer> {

    //TO do multiple like this.
    @Query("SELECT s.ownerName from SportsClub s where s.ownerName = :ownerName ")
    List<String> findOwner(@Param("ownerName") String ownerName);

    @Query("Select s.sportsClubName from SportsClub s where  s.sportsClubName LIKE concat('%', :clubName, '%' ) ")
    List<String> findClubLike(@Param("clubName") String clubName);

    @Query("SELECT s FROM SportsClub s WHERE s.sportsClubName = :clubName")//optional it might return null
    List<SportsClub> findBySportsClubName(@Param("clubName") String clubName);

}
