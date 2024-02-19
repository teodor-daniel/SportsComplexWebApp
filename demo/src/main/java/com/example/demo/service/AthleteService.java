package com.example.demo.service;

import com.example.demo.model.Athlete;
import com.example.demo.model.SportsClub;
import com.example.demo.repository.AthleteRepository;
import com.example.demo.repository.SportsClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class AthleteService {
    private final AthleteRepository athleteRepository;
    private final SportsClubRepository clubRepository;
    @Autowired
    public AthleteService(AthleteRepository athleteRepository, SportsClubRepository clubRepository){
        this.athleteRepository = athleteRepository;
        this.clubRepository = clubRepository;
    }


    public List<SportsClub> findAllClubs() {
        List<Long> clubIds = athleteRepository.findDistinctClubIds();
        System.out.println(clubIds);
        System.out.println((List<SportsClub>) clubRepository.findAllById(clubIds));
        return (List<SportsClub>) clubRepository.findAllById(clubIds);
    }
    public List<Athlete> findAllAthletes(){
        return athleteRepository.findAllAthletes();
    }
}
