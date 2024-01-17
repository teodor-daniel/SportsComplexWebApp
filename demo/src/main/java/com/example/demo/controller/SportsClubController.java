package com.example.demo.controller;

import com.example.demo.model.SportsClub;
import com.example.demo.service.SportsClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/clubs")
public class SportsClubController {

    private final SportsClubService sportsClubService;

    @Autowired
    public SportsClubController(SportsClubService sportsClubService) {
        this.sportsClubService = sportsClubService;
    }

    @GetMapping("")
    public String clubsTable(Model model) {
        List<SportsClub> clubs = sportsClubService.findAllClubs();
        model.addAttribute("clubs", clubs);
        return "club";
    }
    @GetMapping("/add")
    public String addClubs(Model model) {
        model.addAttribute("sportsClub", new SportsClub());
        return "add-club-form";
    }

    @PostMapping("/add")
    public String addClub(@ModelAttribute SportsClub sportsClub, RedirectAttributes redirectAttributes, Model model) {
        boolean success = sportsClubService.addClub(sportsClub);//get a logic response from services.
        if (!success) {
            model.addAttribute("error", "Validation error: Club name or email already exists or owner name is missing.");
            model.addAttribute("sportsClub", sportsClub);
            return "add-club-form";
        }
        return "redirect:/clubs";
    }
    @PostMapping("/delete/{id}")
    public String deleteClub(@PathVariable Long id) {
        sportsClubService.deleteClub(id);
        return "redirect:/clubs";
    }

 


}
