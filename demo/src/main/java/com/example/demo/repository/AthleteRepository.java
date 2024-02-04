package com.example.demo.repository;

import com.example.demo.model.Athlete;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AthleteRepository extends CrudRepository<Athlete , Long> {
    @Query("SELECT count(a) FROM Athlete a WHERE a.sportsClub.id = :clubId")
    Long findClubAthletesNumber(@Param("clubId") Long clubId);

}
