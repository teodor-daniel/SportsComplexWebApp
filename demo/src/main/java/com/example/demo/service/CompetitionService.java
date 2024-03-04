package com.example.demo.service;

import com.example.demo.model.Competition;
import com.example.demo.model.Sponsor;
import com.example.demo.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class CompetitionService {

    private final CompetitionRepository competitionRepository;

    @Autowired
    public CompetitionService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    public List<Competition> findAllCompetitions() {
        return competitionRepository.findAllCompetitions();
    }

    public Competition findById(Long id) {
        return competitionRepository.findById(id).orElse(null);
    }

    @Transactional
    public boolean addCompetition(Competition competition) {
        if (checkConstraints(competition)) {
            competitionRepository.save(competition);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean update(Competition competition) {
        if (checkConstraints(competition) && competition.getId() != null) {
            Competition existingCompetition = findById(competition.getId());
            if (existingCompetition != null) {
                competitionRepository.save(competition);
                return true;
            }
        }
        return false;
    }

    @Transactional
    public void delete(Long id) {
        competitionRepository.deleteById(id);
    }



    private boolean checkConstraints(Competition competition) {
        return true;
    }


}
