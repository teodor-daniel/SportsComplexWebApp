package com.example.demo.repository;

import com.example.demo.model.SponsorshipContract;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SponsorshipContractRepository extends CrudRepository<SponsorshipContract, Long> {

    @NonNull
    @Query("select s  from  SponsorshipContract s order by s.id")
    List<SponsorshipContract> findAll();
}
