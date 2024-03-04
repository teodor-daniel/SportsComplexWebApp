package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "athlete_training_program")
public class AthleteTrainingProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "athlete_id", nullable = false)
    private Athlete athlete;

    @ManyToOne
    @JoinColumn(name = "training_id", nullable = false)
    private Training training;

    @Column(name = "repetitions", nullable = false)
    private Integer repetitions;

    @Column(name = "program_date", nullable = false)
    private java.sql.Date programDate;

    @Column(name = "sport", nullable = false, length = 200)
    private String sport;

    public AthleteTrainingProgram() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public Integer getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(Integer repetitions) {
        this.repetitions = repetitions;
    }

    public java.sql.Date getProgramDate() {
        return programDate;
    }

    public void setProgramDate(java.sql.Date programDate) {
        this.programDate = programDate;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }
    @Override
    public String toString() {
        return "AthleteTrainingProgram{" +
                "id=" + id +
                ", athlete=" + athlete +
                ", training=" + training +
                ", repetitions=" + repetitions +
                ", programDate=" + programDate +
                ", sport='" + sport + '\'' +
                '}';
    }
}
