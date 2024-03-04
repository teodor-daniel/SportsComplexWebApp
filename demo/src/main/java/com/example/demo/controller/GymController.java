package com.example.demo.controller;

import com.example.demo.model.Gym;
import com.example.demo.service.GymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gyms")
public class GymController {
    private final GymService gymService;

    @Autowired
    public GymController(GymService gymService){
        this.gymService = gymService;
    }

    @GetMapping("")
    public String gymTable(Model model) {
        List<Gym> gyms = gymService.findAllGyms();
        model.addAttribute("gyms", gyms);
        return "gyms";
    }

    @GetMapping("/add")
    public String addGymForm(Model model){
        model.addAttribute("gym", new Gym());
        return "add-gym-form";
    }

    @PostMapping("/add")
    public String addGym(@ModelAttribute Gym gym, Model model){
        boolean success = gymService.addGym(gym);
        if(success){
            return "redirect:/gyms";
        } else {
            model.addAttribute("gym", gym);
            model.addAttribute("error", "Check the requirements for adding a gym");
            return "add-gym-form";
        }
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Gym gym = gymService.findById(id);
        if (gym != null) {
            model.addAttribute("gym", gym);
            return "update-gym-form";
        } else {
            return "redirect:/gyms";
        }
    }

    @PostMapping("/update")
    public String updateGym(@ModelAttribute Gym gym, Model model) {
        boolean success = gymService.update(gym);
        if(!success){
            model.addAttribute("error", "Validation error");
            return "update-gym-form";
        }
        return "redirect:/gyms";
    }

    @PostMapping("/delete/{id}")
    public String deleteGym(@PathVariable Long id) {
        gymService.delete(id);
        return "redirect:/gyms";
    }
}
