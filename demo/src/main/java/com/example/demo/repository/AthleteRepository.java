package com.example.demo.repository;

import com.example.demo.model.Athlete;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.List;

public interface AthleteRepository extends CrudRepository<Athlete , Long> {
    @Query("SELECT count(a) FROM Athlete a WHERE a.sportsClub.id = :clubId")
    Long findClubAthletesNumber(@Param("clubId") Long clubId);
    @Query("Select a FROM Athlete a")
    List<Athlete> findAllAthletes();

    @Query("SELECT DISTINCT a.sportsClub.id FROM Athlete a order by a.sportsClub.id")
    List<Long> findDistinctClubIds();
    @Query("SELECT  a.id, a.birthdate, a.gen, a.name, a.phoneNumber, a.sportsClub from Athlete a where  a.id = : athleteId")
    Athlete findbyId(@Param("athleteId") Long athleteId);



boolean existsByPhoneNumber(String phoneNumber);
boolean existsById(@NonNull Long id);


}
