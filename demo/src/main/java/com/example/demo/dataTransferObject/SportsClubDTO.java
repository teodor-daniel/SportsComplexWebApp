package com.example.demo.dataTransferObject;

public class SportsClubDTO {
    private Long id;
    private String ownerName;

    private String sportsClubName;
    private String email;
    private Long athleteCount;

    public SportsClubDTO(Long id, String ownerName, String sportsClubName, String email, Long athleteCount) {
        this.id = id;
        this.ownerName = ownerName;
        this.sportsClubName = sportsClubName;
        this.email = email;
        this.athleteCount = athleteCount;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getAthleteCount() {
        return athleteCount;
    }

    public void setAthleteCount(Long athleteCount) {
        this.athleteCount = athleteCount;
    }

}
