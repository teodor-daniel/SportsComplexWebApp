package com.example.demo.repository;

import com.example.demo.model.Training;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingRepository extends CrudRepository<Training, Long> {

    @NonNull
    @Query("SELECT t FROM Training t ORDER BY t.id")
    List<Training> findAll();

    @Query("FROM Training t WHERE t.id = :id")
    Optional<Training> findById(@NonNull @Param("id") Long id);

    @Query("FROM Training t WHERE t.gym.id = :gymId")
    List<Training> findByGymId(@Param("gymId") Long gymId);

    @Query("FROM Training t WHERE t.coach.id = :coachId")
    List<Training> findByCoachId(@Param("coachId") Long coachId);
}
