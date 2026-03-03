package fr.ensai.running.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ensai.running.model.Competition;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
}