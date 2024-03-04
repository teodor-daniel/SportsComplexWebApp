package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "training")
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sport", nullable = false, length = 200)
    private String sport;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Column(name = "warm_up_duration", nullable = false)
    private Integer warmUpDuration;

    @ManyToOne
    @JoinColumn(name = "gym_id", nullable = false)
    private Gym gym;

    @ManyToOne
    @JoinColumn(name = "coach_id", nullable = false)
    private Coach coach;

    public Training() {}

    public Training(String sport, Integer duration, Integer warmUpDuration, Gym gym, Coach coach) {
        this.sport = sport;
        this.duration = duration;
        this.warmUpDuration = warmUpDuration;
        this.gym = gym;
        this.coach = coach;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getWarmUpDuration() {
        return warmUpDuration;
    }

    public void setWarmUpDuration(Integer warmUpDuration) {
        this.warmUpDuration = warmUpDuration;
    }

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", sport='" + sport + '\'' +
                ", duration=" + duration +
                ", warmUpDuration=" + warmUpDuration +
                ", gym=" + gym +
                ", coach=" + coach +
                '}';
    }
}
