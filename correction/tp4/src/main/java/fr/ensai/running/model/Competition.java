package fr.ensai.running.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_competition")
    private long id;

    private String designation;
    private String city;

    @Column(name = "event_date")
    private LocalDate eventDate;

    private float distance;

    @Column(name = "max_athletes")
    private int maxAthletes;

    @Override
    public String toString() {
        return "Competition (" + designation + ", " + eventDate + ")";
    }

}