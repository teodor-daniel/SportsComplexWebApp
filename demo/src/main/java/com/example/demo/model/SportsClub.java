package com.example.demo.model;


import jakarta.persistence.*;

@Entity
@Table(name = "sports_club")
public class SportsClub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "owner_name", nullable = false)
    private String ownerName;

    @Column(name = "sports_club_name", nullable = false, unique = true)
    private String sportsClubName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;


    public SportsClub(){};

    public SportsClub(String ownerName, String sportsClubName, String email){
        this.ownerName = ownerName;
        this.sportsClubName = sportsClubName;
        this.email = email;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getSportsClubName() {
        return sportsClubName;
    }

    public void setSportsClubName(String sportsClubName) {
        this.sportsClubName = sportsClubName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId(){return  id;}

}
