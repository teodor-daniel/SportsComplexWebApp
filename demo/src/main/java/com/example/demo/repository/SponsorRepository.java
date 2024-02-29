package com.example.demo.repository;

import com.example.demo.model.Sponsor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SponsorRepository extends CrudRepository<Sponsor,Long> {

    @Query("select s from Sponsor s order by s.id")
    List<Sponsor> findAllSponsors();

    @Query("select s from Sponsor s where s.phone = :phone")
    Optional<Sponsor> findByPhone(@Param("phone") String phoneNumber);

    @Query("select s from Sponsor s where s.id = :id")
    Optional<Sponsor> findById(@Param("id") long id);

    @Query("select s from Sponsor s where s.id = :id")
    Sponsor mustFindById(@Param("id") long id);

}
