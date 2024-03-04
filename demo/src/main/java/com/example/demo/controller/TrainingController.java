package com.example.demo.controller;

import com.example.demo.model.Training;
import com.example.demo.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/trainings")
public class TrainingController {

    private final TrainingService trainingService;

    @Autowired
    public TrainingController(TrainingService trainingService){
        this.trainingService = trainingService;
    }

    @GetMapping("")
    public String trainingTable(Model model){
        List<Training> trainings = trainingService.findAllTrainings();
        model.addAttribute("trainings", trainings);
        return "trainings";
    }

    @GetMapping("/add")
    public String addTrainingForm(Model model){
        model.addAttribute("training", new Training());
        model.addAttribute("gyms", trainingService.findAllGyms());
        model.addAttribute("coaches", trainingService.findAllCoaches());
        return "add-training-form";
    }

    @PostMapping("/add")
    public String addTraining(@ModelAttribute Training training, Model model){
        boolean success = trainingService.addTraining(training);
        if(success){
            return "redirect:/trainings";
        } else {
            model.addAttribute("training", training);
            model.addAttribute("gyms", trainingService.findAllGyms());
            model.addAttribute("coaches", trainingService.findAllCoaches());
            model.addAttribute("error", "Check the requirements for adding a training session");
            return "add-training-form";
        }
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Training training = trainingService.findById(id);
        if (training != null) {
            model.addAttribute("training", training);
            model.addAttribute("gyms", trainingService.findAllGyms());
            model.addAttribute("coaches", trainingService.findAllCoaches());
            return "update-training-form";
        } else {
            return "redirect:/trainings";
        }
    }

    @PostMapping("/update")
    public String updateTraining(@ModelAttribute Training training, Model model) {
        boolean success = trainingService.update(training);
        if(!success){
            model.addAttribute("error", "Validation error");
            return "update-training-form";
        }
        return "redirect:/trainings";
    }

    @PostMapping("/delete/{id}")
    public String deleteTraining(@PathVariable Long id) {
        trainingService.delete(id);
        return "redirect:/trainings";
    }
}
