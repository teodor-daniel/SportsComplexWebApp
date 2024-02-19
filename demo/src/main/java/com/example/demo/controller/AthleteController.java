package com.example.demo.controller;

import com.example.demo.model.Athlete;
import com.example.demo.model.SportsClub;
import com.example.demo.service.AthleteService;
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
        model.addAttribute("clubs", athleteService.findAllClubs());
        return "add-athlete-form";
    }



    @PostMapping("/athlete/add")
    public String addAthlete(@ModelAttribute Athlete athlete, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("clubs", athleteService.findAllClubs()); // Repopulate clubs in case of error
            return "add-athlete-form";
        }

        return "redirect:/athletes";
    }
}
