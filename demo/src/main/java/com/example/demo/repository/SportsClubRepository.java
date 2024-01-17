package com.example.demo.repository;

import com.example.demo.model.SportsClub;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SportsClubRepository extends CrudRepository<SportsClub, Integer> {

    //TO do multiple like this.
    @Query("select s.ownerName from SportsClub s where s.ownerName = :ownerName ")
    List<String> findOwner(@Param("ownerName") String ownerName);

    @Query("select s.sportsClubName from SportsClub s where  s.sportsClubName like concat('%', :clubName, '%' ) ")
    List<String> findClubLike(@Param("clubName") String clubName);

    @Query("select s.sportsClubName from SportsClub s where s.sportsClubName = :clubName")//optional it might return null
    Optional<String> findBySportsClubName(@Param("clubName") String clubName);

    @Query("SELECT s.email FROM SportsClub s WHERE s.email = :clubEmail")
    Optional<String> findEmail(@Param("clubEmail") String clubEmail);


    void deleteById(Long id); //implicit method in the ORM


}
