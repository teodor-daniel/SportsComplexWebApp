package com.example.demo.service;

import com.example.demo.model.Coach;
import com.example.demo.model.Gym;
import com.example.demo.model.Training;
import com.example.demo.repository.CoachRepository;
import com.example.demo.repository.GymRepository;
import com.example.demo.repository.TrainingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingService {
    private final TrainingRepository trainingRepository;
    private final GymRepository gymRepository;
    private final CoachRepository coachRepository;

    @Autowired
    public TrainingService(TrainingRepository trainingRepository, GymRepository gymRepository, CoachRepository coachRepository) {
        this.trainingRepository = trainingRepository;
        this.gymRepository = gymRepository;
        this.coachRepository = coachRepository;
    }

    public List<Training> findAllTrainings() {
        return (List<Training>) trainingRepository.findAll();
    }

    public List<Gym> findAllGyms() {
        return (List<Gym>) gymRepository.findAll();
    }

    public List<Coach> findAllCoaches() {
        return (List<Coach>) coachRepository.findAll();
    }

    @Transactional
    public boolean addTraining(Training training) {
        if (checkConstraints(training)) {
            trainingRepository.save(training);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean update(Training training) {
        if (checkConstraints(training) && training.getId() != null) {
            Training existingTraining = findById(training.getId());
            if (existingTraining != null) {
                trainingRepository.save(training);
                return true;
            }
        }
        return false;
    }

    @Transactional
    public void delete(Long id) {
        trainingRepository.deleteById(id);
    }

    private boolean checkConstraints(Training training) {
        return true;
    }

    public Training findById(Long id) {
        return trainingRepository.findById(id).orElse(null);
    }
}
