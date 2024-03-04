package com.example.demo.service;

import com.example.demo.model.Athlete;
import com.example.demo.model.SportsClub;
import com.example.demo.repository.AthleteRepository;
import com.example.demo.repository.SportsClubRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

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
        List<Long> clubIds = clubRepository.findDistinctClubIds();
        return (List<SportsClub>) clubRepository.findAllById(clubIds);
    }

    public boolean addAthlete(Athlete athlete) {
        if(checkConstraint(athlete)){
            athleteRepository.save(athlete);
            return true;
        }
        return false;
    }

    public boolean checkConstraint(Athlete athlete){
        if(athlete.getName().length() > 30){
            return false;
        }
        if(!(athlete.getGen().equals("M") || athlete.getGen().equals("F"))){
            System.out.println("Gender");
            return false;
        }
        if(athlete.getPhoneNumber().length()  != 12){
            return false;
        }
        if(athlete.getBirthdate() == null){
            return false;
        }
        return true;
    }
    @Transactional
    public boolean update(Athlete athlete){
        if(checkConstraint(athlete)){
            Athlete athleteOld = findById(athlete.getId());
            athleteOld.setBirthdate(athlete.getBirthdate());
            athleteOld.setName(athlete.getName());
            athleteOld.setGen(athlete.getGen());
            athleteOld.setPhoneNumber(athlete.getPhoneNumber());
            athleteOld.setSportsClub(athlete.getSportsClub());
            if(athleteOld.getBirthdate() == null){
                return false;
            }
            athleteRepository.save(athleteOld);
            return true;
        }
        return false;
    }
    public Athlete findById(Long id){
        Optional<Athlete> athlete = athleteRepository.findById(id);
        return athlete.orElse(null);
    }

    public boolean existPhoneNumber(Athlete athlete){
        return  false;
    }
    public List<Athlete> findAllAthletes(){
        return athleteRepository.findAllAthletes();
    }

    @Transactional
    public void delete(Long id) {
        athleteRepository.deleteById(id);
    }

}
