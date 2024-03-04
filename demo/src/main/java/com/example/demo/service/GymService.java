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
        if (checkConstraints(gym)) {
            Gym existingGym = findById(gym.getId());
            existingGym.setId(gym.getId());
            existingGym.setName(gym.getName());
            existingGym.setSport(gym.getSport());
            existingGym.setSize(gym.getSize());
            gymRepository.save(gym);
            return true;
        }
        return false;
    }

    @Transactional
    public void delete(Long id) {
        gymRepository.deleteById(id);
    }

    private boolean checkConstraints(Gym gym) {
        if(gym.getId() == null){
            return false;
        }
        if(gym.getName().length() > 200){
            return false;
        }
        if(gym.getSize() < 1){
            return false;
        }
        return true;
    }
}
