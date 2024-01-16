package com.example.demo.controller;

import com.example.demo.model.SportsClub;
import com.example.demo.service.SportsClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String addClub(@ModelAttribute SportsClub sportsClub) {
        sportsClubService.addClub(sportsClub);
        return "redirect:/clubs"; //redirect to clubs
    }

}
