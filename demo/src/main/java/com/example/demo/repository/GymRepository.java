package com.example.demo.repository;

import com.example.demo.model.Gym;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GymRepository extends CrudRepository<Gym, Long> {

    @Query("select g from Gym g order by g.id")
    List<Gym> findAll();

    @Query("select g from Gym g where g.name = :name")
    Optional<Gym> findByName(@Param("name") String name);

    @Query("select g from Gym g where g.size >= :minSize and g.size <= :maxSize")
    List<Gym> findBySizeRange(@Param("minSize") int minSize, @Param("maxSize") int maxSize);

    @Query("select g from Gym g where g.id = :id")
    Optional<Gym> findById(@Param("id") long id);
}
