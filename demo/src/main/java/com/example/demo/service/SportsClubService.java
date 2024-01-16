package com.example.demo.service;

import com.example.demo.model.SportsClub;
import com.example.demo.repository.SportsClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportsClubService {

    private final SportsClubRepository sportsClubRepository;

    @Autowired
    public SportsClubService(SportsClubRepository sportsClubRepository) {
        this.sportsClubRepository = sportsClubRepository;
    }

    public List<SportsClub> findAllClubs() {
        return (List<SportsClub>) sportsClubRepository.findAll();
    }

    //refactor
    public void addClub(SportsClub sportsClub) {
        if(!sportsClubNameExists(sportsClub.getSportsClubName())){
            sportsClubRepository.save(sportsClub);
        }
        throw new IllegalArgumentException("Sports Club Name already exists");


    }


    public boolean sportsClubNameExists(String clubName) {
        return sportsClubRepository.findBySportsClubName(clubName).isEmpty();//if found not good throw illgealArgument
    }

}
