package com.example.demo.controller;

import com.example.demo.dataTransferObject.CoachTrainingDTO;
import com.example.demo.model.Coach;
import com.example.demo.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/coaches")
public class CoachController {
    private final CoachService coachService;

    @Autowired
    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @GetMapping("")
    public String coachTable(Model model) {
        List<CoachTrainingDTO> coaches = coachService.findAllCoachesWithTrainings();
        model.addAttribute("coaches", coaches);
        return "coaches";
    }

    @GetMapping("/add")
    public String addCoachForm(Model model) {
        model.addAttribute("coach", new Coach());
        return "add-coach-form";
    }

    @PostMapping("/add")
    public String addCoach(@ModelAttribute Coach coach, Model model) {
        boolean success = coachService.addCoach(coach);
        if (success) {
            return "redirect:/coaches";
        } else {
            model.addAttribute("coach", coach);
            model.addAttribute("error", "Check the requirements for adding a coach");
            return "add-coach-form";
        }
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Coach coach = coachService.findById(id);
        if (coach != null) {
            model.addAttribute("coach", coach);
            return "update-coach-form";
        } else {
            return "redirect:/coaches";
        }
    }

    @PostMapping("/update")
    public String updateCoach(@ModelAttribute Coach coach, Model model) {
        boolean success = coachService.updateCoach(coach);
        if (success) {
            return "redirect:/coaches";
        }
        model.addAttribute("coach", coach);
        model.addAttribute("error", "Validation error");
        return "update-coach-form";
    }

    @PostMapping("/delete/{id}")
    public String deleteCoach(@PathVariable Long id) {
        coachService.deleteCoach(id);
        return "redirect:/coaches";
    }
}
