package com.example.demo.repository;

import com.example.demo.dataTransferObject.CoachTrainingDTO;
import com.example.demo.model.Coach;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CoachRepository extends CrudRepository<Coach, Long> {

    @Query("select c from Coach c order by c.id")
    List<Coach> findAllCoaches();

    @Query("select c from Coach c where c.name = :name")
    Optional<Coach> findByName(@Param("name") String name);

    @Query("select c from Coach c where c.sport = :sport")
    List<Coach> findBySport(@Param("sport") String sport);

    @Query("select c from Coach c where c.salary >= :minSalary and c.salary <= :maxSalary")
    List<Coach> findBySalaryRange(@Param("minSalary") Integer minSalary, @Param("maxSalary") Integer maxSalary);

    @Query("select c from Coach c where c.gender = :gender")
    List<Coach> findByGender(@Param("gender") String gender);

    @Query("select c from Coach c where c.phone = :phone")
    Optional<Coach> findByPhone(@Param("phone") String phone);


    @Query("select c from Coach c where c.id = :id")
    Coach mustFindById(@Param("id") long id);

    @Query("SELECT new com.example.demo.dataTransferObject.CoachTrainingDTO(c.id, c.name, c.salary, c.sport,c.birthDate, c.gender, c.phone, COUNT(t)) " +
            "FROM Coach c LEFT JOIN c.trainings t " +
            "GROUP BY c.id, c.name, c.salary, c.sport,c.birthDate, c.gender, c.phone")
    List<CoachTrainingDTO> findAllCoachesWithTrainingCount();


}
