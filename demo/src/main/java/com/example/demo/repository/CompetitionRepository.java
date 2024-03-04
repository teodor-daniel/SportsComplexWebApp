package com.example.demo.repository;

import com.example.demo.model.Competition;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompetitionRepository extends CrudRepository<Competition, Long> {

    @Query("select c from Competition c order by c.id")
    List<Competition> findAllCompetitions();

    @Query("select c from Competition c where c.name = :name")
    Optional<Competition> findByName(@Param("name") String name);

    @Query("select c from Competition c where c.startDate <= :date and c.endDate >= :date")
    List<Competition> findByDateWithin(@Param("date") java.sql.Date date);

    @Query("select c from Competition c where c.id = :id")
    Optional<Competition> findById(@Param("id") long id);

    @Query("select c from Competition c where c.id = :id")
    Competition mustFindById(@Param("id") long id);
}
