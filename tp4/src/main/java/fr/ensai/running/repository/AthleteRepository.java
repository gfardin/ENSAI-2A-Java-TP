package fr.ensai.running.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ensai.running.model.Athlete;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {
}