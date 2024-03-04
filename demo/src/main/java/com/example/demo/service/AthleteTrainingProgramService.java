package com.example.demo.service;

import com.example.demo.model.Athlete;
import com.example.demo.model.AthleteTrainingProgram;
import com.example.demo.model.Training;
import com.example.demo.repository.AthleteRepository;
import com.example.demo.repository.AthleteTrainingProgramRepository;
import com.example.demo.repository.TrainingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AthleteTrainingProgramService {
    private final AthleteTrainingProgramRepository athleteTrainingProgramRepository;
    private final AthleteRepository athleteRepository;
    private final TrainingRepository trainingRepository;

    @Autowired
    public AthleteTrainingProgramService(AthleteTrainingProgramRepository athleteTrainingProgramRepository, AthleteRepository athleteRepository, TrainingRepository trainingRepository) {
        this.athleteTrainingProgramRepository = athleteTrainingProgramRepository;
        this.athleteRepository = athleteRepository;
        this.trainingRepository = trainingRepository;
    }

    public List<AthleteTrainingProgram> findAllAthleteTrainingPrograms() {
        return athleteTrainingProgramRepository.findAll();
    }

    public AthleteTrainingProgram findById(Long id) {
        Optional<AthleteTrainingProgram> athleteTrainingProgram = athleteTrainingProgramRepository.findById(id);
        return athleteTrainingProgram.orElse(null);
    }

    @Transactional
    public boolean addAthleteTrainingProgram(AthleteTrainingProgram athleteTrainingProgram) {
        athleteTrainingProgramRepository.save(athleteTrainingProgram);
        return true;
    }

    @Transactional
    public boolean updateAthleteTrainingProgram(AthleteTrainingProgram athleteTrainingProgram) {
        if (athleteTrainingProgram.getId() != null) {
            athleteTrainingProgramRepository.save(athleteTrainingProgram);
            return true;
        }
        return false;
    }


    public boolean checkConstraints(AthleteTrainingProgram athleteTrainingProgram){
        if(athleteTrainingProgram.getId() == null){
            System.out.println("Check id, it is null");
            return false;
        }
        if(athleteTrainingProgram.getAthlete() == null){
            System.out.println("Check athlete, it is null");
            return false;
        }
        if(athleteTrainingProgram.getTraining() == null){
            System.out.println("Check training, it is null");
            return false;
        }
        if(athleteTrainingProgram.getSport().length() > 200){
            System.out.println("Check sport length");
            return false;
        }
        return true;
    }
    @Transactional
    public void delete(Long id) {
        athleteTrainingProgramRepository.deleteById(id);
    }

    public List<Athlete> findAllAthletesWithParticipations() {
        return athleteRepository.findAllAthletes();
    }

    public List<Training> findAllTrainingsWithParticipations() {
        return trainingRepository.findAll();
    }
}
