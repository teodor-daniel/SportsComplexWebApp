package com.example.demo.controller;

import com.example.demo.model.SponsorshipContract;
import com.example.demo.service.SponsorshipContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
            model.addAttribute("error", "Check the requirements for adding a contract");
            return "add-contract-from";
        }
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        SponsorshipContract contract = contractService.findById(id);
        if (contract != null) {
            System.out.println("Start Date: " + contract.getStartDate());
            System.out.println("End Date: " + contract.getEndDate());
            model.addAttribute("contract", contract);
            model.addAttribute("sponsors", contractService.findAllSponsorsWithContracts());
            model.addAttribute("athletes", contractService.findAllAthletesWithContracts());
            return "update-contract-form";
        } else {
            return "redirect:/contracts";
        }
    }

    @PostMapping("/update")
    public String updateClub(@ModelAttribute SponsorshipContract contract, Model model) {
        boolean success = contractService.update(contract);
        if(!success){
            model.addAttribute("error", "Validation error");
            return "update-contract-form";
        }
        return "redirect:/contracts";
    }

    @PostMapping("/delete/{id}")
    public String deleteClub(@PathVariable Long id) {
        contractService.delete(id);
        return "redirect:/contracts";
    }
}
