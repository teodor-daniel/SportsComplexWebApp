package com.example.demo.repository;

import com.example.demo.model.SponsorshipContract;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SponsorshipContractRepository extends CrudRepository<SponsorshipContract, Long> {

    @NonNull
    @Query("select s  from  SponsorshipContract s order by s.id")
    List<SponsorshipContract> findAll();


    @Query("FROM SponsorshipContract s WHERE s.id = :id")
    Optional<SponsorshipContract> findById(@NonNull @Param("id") Long id);
;

}
