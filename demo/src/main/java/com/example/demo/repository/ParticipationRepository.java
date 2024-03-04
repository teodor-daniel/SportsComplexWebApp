package com.example.demo.repository;

import com.example.demo.model.Participation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipationRepository extends CrudRepository<Participation, Long> {

    @NonNull
    @Query("SELECT p FROM Participation p ORDER BY p.id")
    List<Participation> findAll();

    @Query("FROM Participation p WHERE p.id = :id")
    Optional<Participation> findById(@NonNull @Param("id") Long id);

    @Query("FROM Participation p WHERE p.athlete.id = :athleteId")
    List<Participation> findByAthleteId(@Param("athleteId") Long athleteId);

    @Query("FROM Participation p WHERE p.competition.id = :competitionId")
    List<Participation> findByCompetitionId(@Param("competitionId") Long competitionId);
}
