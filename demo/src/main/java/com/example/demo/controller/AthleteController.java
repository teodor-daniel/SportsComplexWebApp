package com.example.demo.controller;

import com.example.demo.model.Athlete;
import com.example.demo.model.SportsClub;
import com.example.demo.service.AthleteService;
import com.example.demo.service.SportsClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/athletes")
public class AthleteController {
    private final AthleteService athleteService;
    @Autowired
    public AthleteController(AthleteService athleteService){
        this.athleteService = athleteService;
    }

    @GetMapping("")
    public String athletes(Model model){
        List<Athlete> athletes =  athleteService.findAllAthletes();
        model.addAttribute("athlete", athletes);
        return "athletes";
    }
    @GetMapping("/add")
    public String addAthleteForm(Model model) {
        model.addAttribute("athlete", new Athlete());
        model.addAttribute("clubs", athleteService.findAllClubs()); //find all the clubs that contain an athlete to show in the dropdown
        return "add-athlete-form";
    }

    @PostMapping("/add")
    public String addAthlete(@ModelAttribute Athlete athlete, Model model) {
        boolean success = athleteService.addAthlete(athlete);
        if (success) {
            return "redirect:/athletes";
        } else {
            model.addAttribute("error", "Check the requirements for adding an athlete");
            model.addAttribute("athlete", athlete);
            model.addAttribute("clubs", athleteService.findAllClubs());
            model.addAttribute("error", "Validation error:" + athlete.getId());
            return "add-athlete-form";
        }
    }
}
