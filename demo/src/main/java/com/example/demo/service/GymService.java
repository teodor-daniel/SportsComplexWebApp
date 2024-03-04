package com.example.demo.service;

import com.example.demo.model.Gym;
import com.example.demo.repository.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class GymService {

    private final GymRepository gymRepository;

    @Autowired
    public GymService(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
    }

    public List<Gym> findAllGyms() {
        return gymRepository.findAll();
    }

    public Gym findById(Long id) {
        return gymRepository.findById(id).orElse(null);
    }

    @Transactional
    public boolean addGym(Gym gym) {
        if (checkConstraints(gym)) {
            gymRepository.save(gym);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean update(Gym gym) {
        if (checkConstraints(gym) && gym.getId() != null) {
            Gym existingGym = findById(gym.getId());
            if (existingGym != null) {
                gymRepository.save(gym);
                return true;
            }
        }
        return false;
    }

    @Transactional
    public void delete(Long id) {
        gymRepository.deleteById(id);
    }

    private boolean checkConstraints(Gym gym) {
        // Implement constraint checks as needed
        return true;
    }
}
