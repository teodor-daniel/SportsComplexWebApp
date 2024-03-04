package com.example.demo.controller;

import com.example.demo.model.Participation;
import com.example.demo.service.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/participations")
public class ParticipationController {

    private final ParticipationService participationService;

    @Autowired
    public ParticipationController(ParticipationService participationService){
        this.participationService = participationService;
    }

    @GetMapping("")
    public String participationTable(Model model){
        List<Participation> participations = participationService.findAllParticipations();
        model.addAttribute("participations", participations);
        return "participations";
    }

    @GetMapping("/add")
    public String addParticipationForm(Model model){
        model.addAttribute("participation", new Participation());
        model.addAttribute("athletes", participationService.findAllAthletesWithParticipations());
        model.addAttribute("competitions", participationService.findAllCompetitionsWithParticipations());
        return "add-participation-form";
    }

    @PostMapping("/add")
    public String addParticipation(@ModelAttribute Participation participation, Model model){
        boolean success = participationService.addParticipation(participation);
        if(success){
            return "redirect:/participations";
        } else {
            model.addAttribute("error", "Check the requirements for adding a participation");
            model.addAttribute("athletes", participationService.findAllAthletesWithParticipations());
            model.addAttribute("competitions", participationService.findAllCompetitionsWithParticipations());
            return "add-participation-form";
        }
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Participation participation = participationService.findById(id);
        if (participation != null) {
            model.addAttribute("participation", participation);
            model.addAttribute("athletes", participationService.findAllAthletesWithParticipations());
            model.addAttribute("competitions", participationService.findAllCompetitionsWithParticipations());
            return "update-participation-form";
        } else {
            return "redirect:/participations";
        }
    }

    @PostMapping("/update")
    public String updateParticipation(@ModelAttribute Participation participation, Model model) {
        boolean success = participationService.update(participation);
        if(!success){
            model.addAttribute("error", "Validation error");
            return "update-participation-form";
        }
        return "redirect:/participations";
    }

    @PostMapping("/delete/{id}")
    public String deleteParticipation(@PathVariable Long id) {
        participationService.delete(id);
        return "redirect:/participations";
    }
}
