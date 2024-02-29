package com.example.demo.service;

import com.example.demo.dataTransferObject.SportsClubDTO;
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
    public List<SportsClubDTO> findAllClubsWithAthleteCount() {
        return sportsClubRepository.findAllClubsWithAthletesCount();
    }

    //refactor
    public boolean addClub(SportsClub sportsClub) {

        if(!sportsClubNameExist(sportsClub.getSportsClubName()) ){
            return false;
        }
        if(!sportsClubEmailExist(sportsClub.getEmail()) ){
            return false;
        }
        if(!checkConstraints(sportsClub)){
            return false;
        }
        sportsClubRepository.save(sportsClub);
        return true;
    }

    public boolean checkConstraints(SportsClub sportsClub){
        if(sportsClub.getOwnerName().length() > 200){
            return false;
        }
        if(sportsClub.getSportsClubName().length() > 200){
            return false;
        }
        if(sportsClub.getEmail().length() > 100){
            return false;
        }
        return true;
    }

    public SportsClub findClubById(Long id) {
        Optional<SportsClub> club = sportsClubRepository.findById(id);
        return club.orElse(null); // Returns the club if found, otherwise returns null
    }


    public boolean sportsClubNameExist(String clubName) {
        return sportsClubRepository.findBySportsClubName(clubName) != null;
    }


    public boolean sportsClubEmailExist(String clubName){
       return sportsClubRepository.findEmail(clubName) != null;
    }

    public boolean sportsClubOwnerNameExist(String clubOwner){
        if(sportsClubRepository.findOwner(clubOwner) == null){
            return false;
        }
        return true;
    }

    @Transactional
    public boolean update(SportsClub sportsClubNew) {
        Optional<SportsClub> sportsClubOldOptional = sportsClubRepository.findById(sportsClubNew.getId());
        if (sportsClubOldOptional.isPresent()) {
            SportsClub sportsClubOld = sportsClubOldOptional.get();
            if(sportsClubNameExist(sportsClubNew.getSportsClubName()) || sportsClubEmailExist(sportsClubNew.getEmail()) || sportsClubOwnerNameExist(sportsClubNew.getOwnerName())){
                return false;
            }
            sportsClubOld.setOwnerName(sportsClubNew.getOwnerName());
            sportsClubOld.setSportsClubName(sportsClubNew.getSportsClubName());
            sportsClubOld.setEmail(sportsClubNew.getEmail());
            sportsClubRepository.save(sportsClubOld);
            return true;
        }
        return false;
    }



    @Transactional
    public void delete(Long id) {
        sportsClubRepository.deleteById(id); //this must be executed in a transaction context
    }


}
