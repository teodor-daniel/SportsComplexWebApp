package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "sponsorship_contract")
public class SponsorshipContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sponsorship_contract")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_athlete", nullable = false)
    private Athlete athlete;

    @ManyToOne
    @JoinColumn(name = "id_sponsor", nullable = false)
    private Sponsor sponsor;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private Double amount;

    public SponsorshipContract() {}

    public SponsorshipContract(Athlete athlete, Sponsor sponsor, LocalDate startDate, LocalDate endDate, Double amount) {
        this.athlete = athlete;
        this.sponsor = sponsor;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
    }

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

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "SponsorshipContract{" +
                "id=" + id +
                ", athlete=" + athlete +
                ", sponsor=" + sponsor +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", amount=" + amount +
                '}';
    }
}
