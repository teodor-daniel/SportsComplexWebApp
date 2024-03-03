package com.example.demo.service;

import com.example.demo.model.Sponsor;
import com.example.demo.repository.SponsorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SponsorService {

    private final SponsorRepository sponsorRepository;

    public SponsorService(SponsorRepository sponsorRepository) {
        this.sponsorRepository = sponsorRepository;
    }

    public List<Sponsor> findAllSponsors(){
        List<Sponsor> sponsorList = sponsorRepository.findAllSponsors();
        return sponsorList;
    }

    public boolean addSponsor(Sponsor sponsor){
        if(!checkConstraints(sponsor)){
            return false;
        }
        sponsorRepository.save(sponsor);
        return true;
    }

    public boolean checkConstraints(Sponsor sponsor){
        if(sponsor.getName().length() > 30){
            return false;
        }
        if(sponsor.getAddress().length() > 30){
            return false;
        }
        if(sponsor.getPhone().length() != 12){
            return false;
        }
        if(sponsorRepository.findByPhone(sponsor.getPhone()) == null){
            System.out.println("hello");
            return false;
        }

        return true;
    }

    public Sponsor findById(Long id){
        return sponsorRepository.mustFindById(id);
    }

    @Transactional
    public boolean update(Sponsor sponsor){
        if(checkConstraints(sponsor)){
            Sponsor oldSponsor = sponsorRepository.mustFindById(sponsor.getId());
            sponsorRepository.save(oldSponsor);
            return true;
        }
        return false;
    }

@Transactional
    public void delete(Long id){
        sponsorRepository.deleteById(id);
    }
}
