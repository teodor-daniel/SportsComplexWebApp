package com.example.demo.model;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

@Entity
@Table(name = "athlete")
public class Athlete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)

    private String name;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false, length = 1)
    private String gen;

    @Column(length = 12, unique = true, nullable = false)
    private String phoneNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sports_club_id", nullable = false)
    private SportsClub sportsClub;

    public  Athlete(){}

    public Athlete(String name, LocalDate birthdate, String gen, String phoneNumber, SportsClub sportsClub) {
        this.name = name;
        this.birthdate = birthdate;
        this.gen = gen;
        this.phoneNumber = phoneNumber;
        this.sportsClub = sportsClub;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }


    public String getGen(){
        return gen;
    }

    public void setGen(String gen){
        this.gen = gen;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public SportsClub getSportsClub() {
        return sportsClub;
    }

    public void setSportsClub(SportsClub sportsClub) {
        this.sportsClub = sportsClub;
    }


}
