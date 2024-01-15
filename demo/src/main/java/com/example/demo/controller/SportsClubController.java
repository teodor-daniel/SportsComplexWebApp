package com.example.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.demo.model.SportsClub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.repository.SportsClubRepository;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/clubs") // Define the base route for this controller
public class SportsClubController {

    private final SportsClubRepository sportsClubRepository;

    @Autowired
    public SportsClubController(SportsClubRepository sportsClubRepository) {
        this.sportsClubRepository = sportsClubRepository;
    }

    @GetMapping
    public List<SportsClub> findAllClubs() {
        return (List<SportsClub>) this.sportsClubRepository.findAll(); // Fetches all sports clubs
    }





}
