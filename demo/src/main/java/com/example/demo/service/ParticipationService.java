package com.example.demo.service;

import com.example.demo.model.Athlete;
import com.example.demo.model.Competition;
import com.example.demo.model.Participation;
import com.example.demo.repository.AthleteRepository;
import com.example.demo.repository.CompetitionRepository;
import com.example.demo.repository.ParticipationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipationService {
    private final ParticipationRepository participationRepository;
    private final AthleteRepository athleteRepository;
    private final CompetitionRepository competitionRepository;

    @Autowired
    public ParticipationService(ParticipationRepository participationRepository, AthleteRepository athleteRepository, CompetitionRepository competitionRepository) {
        this.participationRepository = participationRepository;
        this.athleteRepository = athleteRepository;
        this.competitionRepository = competitionRepository;
    }

    public List<Participation> findAllParticipations() {
        return participationRepository.findAll();
    }

    public List<Athlete> findAllAthletesWithParticipations(){
        return athleteRepository.findAllAthletes();

    }
    public List<Competition> findAllCompetitionsWithParticipations() {
        return competitionRepository.findAllCompetitions();
    }

    @Transactional
    public boolean addParticipation(Participation participation) {
        if(checkConstraints(participation)){
            participationRepository.save(participation);
            return true;
        }
        return false;
    }

    @Transactional
    public void delete(Long id) {
        participationRepository.deleteById(id);
    }

    @Transactional
    public boolean update(Participation participation) {
        if (checkConstraints(participation)) {
            participationRepository.save(participation);
            return true;
        }
        return false;
    }


    public boolean checkConstraints(Participation participation){
        if(participation.getId() == null){
            System.out.println("Check id value");
            return false;
        }
        if(participation.getAthlete() == null){
            System.out.println("Check athlete value");
            return false;
        }
        if(participation.getSport() == null){
            System.out.println("Check Sports value");
            return false;
        }

        if(participation.getSport().length() > 200){
            System.out.println("Check Length of Sport");
            return false;
        }
        return true;
    }

    public Participation findById(Long id) {
        Optional<Participation> participation = participationRepository.findById(id);
        return participation.orElse(null);
    }
}
