package com.example.demo.controller;

import com.example.demo.model.Competition;
import com.example.demo.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/competitions")
public class CompetitionController {
    private final CompetitionService competitionService;

    @Autowired
    public CompetitionController(CompetitionService competitionService){
        this.competitionService = competitionService;
    }

    @GetMapping("")
    public String competitionTable(Model model) {
        List<Competition> competitions = competitionService.findAllCompetitions();
        model.addAttribute("competitions", competitions);
        return "competitions";
    }

    @GetMapping("/add")
    public String addCompetitionForm(Model model){
        model.addAttribute("competition", new Competition());
        return "add-competition-form";
    }

    @PostMapping("/add")
    public String addCompetition(@ModelAttribute Competition competition, Model model){
        boolean success = competitionService.addCompetition(competition);
        if(success){
            return "redirect:/competitions";
        } else {
            model.addAttribute("competition", competition);
            model.addAttribute("error", "Check the requirements for adding a competition");
            return "add-competition-form";
        }
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Competition competition = competitionService.findById(id);
        if (competition != null) {
            model.addAttribute("competition", competition);
            return "update-competition-form";
        } else {
            return "redirect:/competitions";
        }
    }

    @PostMapping("/update")
    public String updateCompetition(@ModelAttribute Competition competition, Model model) {
        boolean success = competitionService.update(competition);
        if(success){
            return "redirect:/competitions";
        }
        model.addAttribute("competition", competition);
        model.addAttribute("error", "Validation error");
        return "update-competition-form";

    }

    @PostMapping("/delete/{id}")
    public String deleteCompetition(@PathVariable Long id) {
        competitionService.delete(id);
        return "redirect:/competitions";
    }
}
