package com.example.demo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "gym")
public class Gym {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 200)
    private String name;

    @Column(name = "sports", nullable = false, length = 200)
    private String sport;

    @Column(name = "size", nullable = false)
    private Integer size;

    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL)
    private List<Training> trainings = new ArrayList<>();
    public Gym() {}

    public Gym(String name, String sport, Integer size) {
        this.name = name;
        this.sport = sport;
        this.size = size;
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

    public String getSport() {
        return sport;
    }

    public void setSport(String sports) {
        this.sport = sports;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    @Override
    public String toString() {
        return "Gym{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sports='" + sport + '\'' +
                ", size=" + size +
                '}';
    }
}
