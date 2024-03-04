package com.example.demo.controller;

import com.example.demo.model.AthleteTrainingProgram;
import com.example.demo.service.AthleteService;
import com.example.demo.service.AthleteTrainingProgramService;
import com.example.demo.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/athletes-trainings-programs")
public class AthleteTrainingProgramController {

    private final AthleteTrainingProgramService athleteTrainingProgramService;
    private final AthleteService athleteService;
    private final TrainingService trainingService;

    @Autowired
    public AthleteTrainingProgramController(AthleteTrainingProgramService athleteTrainingProgramService, AthleteService athleteService, TrainingService trainingService){
        this.athleteTrainingProgramService = athleteTrainingProgramService;
        this.athleteService = athleteService;
        this.trainingService = trainingService;
    }

    @GetMapping("")
    public String athleteTrainingProgramTable(Model model){
        List<AthleteTrainingProgram> athleteTrainingPrograms = athleteTrainingProgramService.findAllAthleteTrainingPrograms();
        model.addAttribute("athleteTrainingPrograms", athleteTrainingPrograms);
        return "athletes-trainings-programs";
    }

    @GetMapping("/add")
    public String addAthleteTrainingProgramForm(Model model){
        model.addAttribute("athleteTrainingProgram", new AthleteTrainingProgram());
        model.addAttribute("athletes", athleteService.findAllAthletes());
        model.addAttribute("trainings", trainingService.findAllTrainings());
        return "add-athlete-training-program-form";
    }

    @PostMapping("/add")
    public String addAthleteTrainingProgram(@ModelAttribute AthleteTrainingProgram athleteTrainingProgram, Model model){
        boolean success = athleteTrainingProgramService.addAthleteTrainingProgram(athleteTrainingProgram);
        if(success){
            return "redirect:/athletes-trainings-programs";
        } else {
            model.addAttribute("error", "Check the requirements for adding a training program");
            model.addAttribute("athletes", athleteService.findAllAthletes());
            model.addAttribute("trainings", trainingService.findAllTrainings());
            return "add-athlete-training-program-form";
        }
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        AthleteTrainingProgram athleteTrainingProgram = athleteTrainingProgramService.findById(id);
        if (athleteTrainingProgram != null) {
            model.addAttribute("athleteTrainingProgram", athleteTrainingProgram);
            model.addAttribute("athletes", athleteService.findAllAthletes());
            model.addAttribute("trainings", trainingService.findAllTrainings());
            return "update-athlete-training-program-form";
        } else {
            return "redirect:/athletes-trainings-programs";
        }
    }

    @PostMapping("/update")
    public String updateAthleteTrainingProgram(@ModelAttribute AthleteTrainingProgram athleteTrainingProgram, Model model) {
        boolean success = athleteTrainingProgramService.updateAthleteTrainingProgram(athleteTrainingProgram);
        if(!success){
            model.addAttribute("error", "Validation error");
            return "update-athlete-training-program-form";
        }
        return "redirect:/athletes-trainings-programs";
    }

    @PostMapping("/delete/{id}")
    public String deleteAthleteTrainingProgram(@PathVariable Long id) {
        athleteTrainingProgramService.delete(id);
        return "redirect:/athletes-trainings-programs";
    }
}
