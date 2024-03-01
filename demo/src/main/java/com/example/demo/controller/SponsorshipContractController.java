package com.example.demo.controller;

import com.example.demo.model.Athlete;
import com.example.demo.model.Sponsor;
import com.example.demo.model.SponsorshipContract;
import com.example.demo.service.SponsorService;
import com.example.demo.service.SponsorshipContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/contracts")
public class SponsorshipContractController {

    private final SponsorshipContractService contractService;

    @Autowired
    public SponsorshipContractController(SponsorshipContractService contractService){
        this.contractService = contractService;
    }
    @GetMapping("")
    public String contractTable(Model model){
        List<SponsorshipContract> contracts = contractService.findAllContracts();
        model.addAttribute("contract", contracts);
        return "contracts";
    }


    @GetMapping("/add")
    public String addContractForm(Model model){
        model.addAttribute("contract", new SponsorshipContract());
        model.addAttribute("sponsors", contractService.findAllSponsors()); //lists of all the sponsors/athletes
        model.addAttribute("athletes", contractService.findAllAthletes());
        return "add-contract-from";
    }

    @PostMapping("/add")
    public String addContract(@ModelAttribute SponsorshipContract contract, Model model){
        boolean success = contractService.addContract(contract);
        if(success){
            model.addAttribute("contract", contract);
            model.addAttribute("sponsors", contractService.findAllSponsors()); //lists of all the sponsors/athletes
            model.addAttribute("athletes", contractService.findAllAthletes());
            return "redirect:/contracts";
        }
        else{
            model.addAttribute("error", "Check the requirements for adding an athlete");
            return "add-contract-from";
        }
    }
}
