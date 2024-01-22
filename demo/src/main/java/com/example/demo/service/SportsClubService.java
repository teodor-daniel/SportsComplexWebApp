package com.example.demo.service;

import com.example.demo.model.SportsClub;
import com.example.demo.repository.SportsClubRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public boolean addClub(SportsClub sportsClub) {
        if(sportsClubNameExist(sportsClub.getSportsClubName()) && sportsClubEmailExist(sportsClub.getEmail()) ){//if no email exists save
            sportsClubRepository.save(sportsClub);

            return true;
        }
        return false;
    }
    public SportsClub findClubById(Long id) {
        Optional<SportsClub> club = sportsClubRepository.findById(id);
        return club.orElse(null); // Returns the club if found, otherwise returns null
    }


    public boolean sportsClubNameExist(String clubName) {
    if(sportsClubRepository.findBySportsClubName(clubName) == null){
        return false;
    }
    return true;
    }

    public boolean sportsClubEmailExist(String clubName){
       if(sportsClubRepository.findEmail(clubName) == null ){
           return false;
       }
       return true;
    }

    @Transactional
    public void updateClub(SportsClub sportsClubNew) {
        Optional<SportsClub> sportsClubOldOptional = sportsClubRepository.findById(sportsClubNew.getId());
        if (sportsClubOldOptional.isPresent()) {
            SportsClub sportsClubOld = sportsClubOldOptional.get();
            sportsClubOld.setOwnerName(sportsClubNew.getOwnerName());
            sportsClubOld.setSportsClubName(sportsClubNew.getSportsClubName());
            sportsClubOld.setEmail(sportsClubNew.getEmail());
            sportsClubRepository.save(sportsClubOld);
        }
    }



    @Transactional
    public void deleteClub(Long id) {
        sportsClubRepository.deleteById(id); //this must be executed in a transaction context
    }


}
