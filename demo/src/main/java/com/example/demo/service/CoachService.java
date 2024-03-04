package com.example.demo.service;

import com.example.demo.model.Coach;
import com.example.demo.repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CoachService {

    private final CoachRepository coachRepository;

    @Autowired
    public CoachService(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    public List<Coach> findAllCoaches() {
        return (List<Coach>) coachRepository.findAll();
    }

    public Coach findById(Long id){
        Optional<Coach> coach = coachRepository.findById(id);
        return coach.orElse(null);
    }

    @Transactional
    public boolean addCoach(Coach coach) {
        if(checkConstraint(coach)){
            coachRepository.save(coach);
            return true;
        }
        return false;

    }

    @Transactional
    public boolean updateCoach(Coach coach) {
        System.out.println(coach);
        if (checkConstraint(coach)) {
                Coach oldCoach = findById(coach.getId());
                oldCoach.setId(coach.getId());
                oldCoach.setName(coach.getName());
                oldCoach.setSalary(coach.getSalary());
                oldCoach.setSport(coach.getSport());
                oldCoach.setBirthDate(coach.getBirthDate());
                oldCoach.setGender(coach.getGender());
                oldCoach.setPhone(coach.getPhone());
                coachRepository.save(oldCoach);
                return true;
            }
        return false;
    }


    public boolean checkConstraint(Coach coach){
        if(!(coach.getGender().equals("M") || coach.getGender().equals("F") )){
            return false;
        }
        if(coach.getSalary() < 0){
            return false;
        }
        if(coach.getPhone().length() != 12) {
            return false;
        }
        if(coach.getName().length() > 200){
            return false;
        }
        return true;
    }

    @Transactional
    public void deleteCoach(Long id) {
        coachRepository.deleteById(id);
    }


}
