package com.example.demo.service;

import com.example.demo.model.Athlete;
import com.example.demo.model.Sponsor;
import com.example.demo.model.SponsorshipContract;
import com.example.demo.repository.AthleteRepository;
import com.example.demo.repository.SponsorRepository;
import com.example.demo.repository.SponsorshipContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SponsorshipContractService {
    private final SponsorshipContractRepository sponsorshipContractRepository;

    private final AthleteRepository athleteRepository;

    private final SponsorRepository sponsorRepository;

    @Autowired
    public SponsorshipContractService(SponsorshipContractRepository sponsorshipContractRepository, AthleteRepository athleteRepository, SponsorRepository sponsorRepository) {
        this.sponsorshipContractRepository = sponsorshipContractRepository;
        this.athleteRepository = athleteRepository;
        this.sponsorRepository = sponsorRepository;
    }


    public List<SponsorshipContract> findAllContracts(){
        return sponsorshipContractRepository.findAll();
    }

    public List<Athlete> findAllAthletes(){
        return athleteRepository.findAllAthletes();
    }

    public List<Sponsor> findAllSponsors(){
        return sponsorRepository.findAllSponsors();
    }

    public boolean addContract(SponsorshipContract contract){
        if(checkConstraint(contract)){
            sponsorshipContractRepository.save(contract);
            return true;
        }

        return false;
    }

    public boolean checkConstraint(SponsorshipContract contract){
        if(contract.getAmount() < 0){
            return false;
        }
        if(contract.getStartDate().isAfter(contract.getEndDate())){
            return false;
        }
        return true;
    }
}
