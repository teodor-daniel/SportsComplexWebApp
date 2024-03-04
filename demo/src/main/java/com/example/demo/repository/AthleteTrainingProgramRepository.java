package com.example.demo.repository;

import com.example.demo.model.AthleteTrainingProgram;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AthleteTrainingProgramRepository extends CrudRepository<AthleteTrainingProgram, Long> {

    @Query("SELECT atp FROM AthleteTrainingProgram atp ORDER BY atp.id")
    List<AthleteTrainingProgram> findAll();

    @Query("FROM AthleteTrainingProgram atp WHERE atp.id = :id")
    Optional<AthleteTrainingProgram> findById(@Param("id") Long id);

    @Query("FROM AthleteTrainingProgram atp WHERE atp.athlete.id = :athleteId")
    List<AthleteTrainingProgram> findByAthleteId(@Param("athleteId") Long athleteId);

    @Query("FROM AthleteTrainingProgram atp WHERE atp.training.id = :trainingId")
    List<AthleteTrainingProgram> findByTrainingId(@Param("trainingId") Long trainingId);

    @Query("FROM AthleteTrainingProgram atp WHERE atp.programDate = :programDate")
    List<AthleteTrainingProgram> findByProgramDate(@Param("programDate") java.sql.Date programDate);

    @Query("FROM AthleteTrainingProgram atp WHERE atp.sport = :sport")
    List<AthleteTrainingProgram> findBySport(@Param("sport") String sport);
}
