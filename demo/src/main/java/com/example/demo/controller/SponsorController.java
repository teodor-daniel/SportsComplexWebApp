package com.example.demo.controller;

import com.example.demo.model.Athlete;
import com.example.demo.model.Sponsor;
import com.example.demo.service.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/sponsors")
public class SponsorController {
    private final SponsorService sponsorService;

    @Autowired
    public SponsorController(SponsorService sponsorService){
        this.sponsorService = sponsorService;
    }

    @GetMapping("")
    public String sponsorTable(Model model){
        List<Sponsor> sponsor = sponsorService.findAllSponsors();
        model.addAttribute("sponsor", sponsor);
        return "sponsors";
    }

    @GetMapping("/add")
    public String addSponsorForm(Model model){
        model.addAttribute("sponsor", new Sponsor());
        return "add-sponsor-form";
    }

    @PostMapping("/add")
    public String addSponsor(@ModelAttribute Sponsor sponsor, Model model){
        boolean success = sponsorService.addSponsor(sponsor);
        if(success){
            return "redirect:/sponsors";
        } else {
            model.addAttribute("sponsor", sponsor);
            model.addAttribute("error", "Check the requirements for adding an athlete");
            return "add-sponsor-form";
        }
    }


    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
       Sponsor sponsor = sponsorService.findById(id);
        if (sponsor != null) {
            model.addAttribute("sponsor", sponsor);
            return "update-sponsor-form";
        } else {
            return "redirect:/sponsor";
        }
    }

    @PostMapping("/update")
    public String updateClub(@ModelAttribute Sponsor sponsor, Model model) {

        boolean success = sponsorService.update(sponsor);
        if(!success){
            model.addAttribute("error", "Validation error");
            return "update-sponsor-form";
        }
        return "redirect:/sponsors";
    }

    @PostMapping("/delete/{id}")
    public String deleteClub(@PathVariable Long id) {
        sponsorService.delete(id);
        return "redirect:/sponsors";
    }
}
