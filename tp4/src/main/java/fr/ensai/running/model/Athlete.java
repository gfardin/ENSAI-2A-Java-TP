package fr.ensai.running.model;

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
public class Athlete {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_athlete")
    private long id;

    private String firstName;
    private String lastName;
    private int age;
    private String gender;

    @Override
    public String toString() {
        return "Athlete (" + firstName + " " + lastName + ", " + age + ")";
    }

}
