package com.example.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.demo.model.SportsClub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.repository.SportsClubRepository;

import java.util.List;
@Controller
@RequestMapping("/clubs")
public class SportsClubController {

    private final SportsClubRepository sportsClubRepository;

    @Autowired
    public SportsClubController(SportsClubRepository sportsClubRepository) {
        this.sportsClubRepository = sportsClubRepository;
    }


    @GetMapping("")
    public String clubsTable(Model model) {
        List<SportsClub> clubs = (List<SportsClub>) this.sportsClubRepository.findAll();
        model.addAttribute("clubs", clubs);
        return "table";
    }



}

