package fr.ensai.running.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.ensai.running.model.Athlete;
import fr.ensai.running.model.Competition;
import fr.ensai.running.model.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    boolean existsByAthleteIdAndCompetitionId(Long athleteId, Long competitionId);

    @Query("SELECT r.athlete.id " +
            "FROM Registration r " +
            "WHERE r.competition.id = :competitionId")
    List<Long> findAthleteIdByCompetitionId(@Param("competitionId") Long competitionId);

    Registration findByAthleteAndCompetition(Athlete athlete, Competition competition);

}
