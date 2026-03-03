package fr.ensai.running.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ensai.running.model.Athlete;
import fr.ensai.running.model.Competition;
import fr.ensai.running.model.Registration;
import fr.ensai.running.repository.AthleteRepository;
import fr.ensai.running.repository.CompetitionRepository;
import fr.ensai.running.repository.RegistrationRepository;

@Service
public class CompetitionService {

    private static final Logger log = LoggerFactory.getLogger(CompetitionService.class);

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private AthleteRepository athleteRepository;

    /**
     * List of all Competitions
     */
    public List<Competition> findAll() {
        return competitionRepository.findAll();
    }

    /**
     * Find a Competition by its id
     */
    public Competition findById(Long id) {
        return competitionRepository.findById(id).orElse(null);
    }

    /**
     * Delete a Competition from its id
     */
    public void deleteById(Long id_competition) {

        List<Athlete> athletes = this.findRegisteredAthletes(id_competition);

        for (Athlete a : athletes) {
            Registration registration = this.findByIdAthleteAndIdCompetition(a.getId(), id_competition);
            this.deleteRegistrationById(registration.getId());
        }

        competitionRepository.deleteById(id_competition);
        log.warn("Competition {} deleted (including {} Registrations)", id_competition, athletes.size());
    }

    /**
     * Register an Athlete for a Competition
     */
    public Registration registerAthlete(Long athleteId, Long competitionId) {
        Athlete athlete = athleteRepository.findById(athleteId).orElse(null);
        Competition competition = competitionRepository.findById(competitionId).orElse(null);

        if (athlete == null || competition == null) {
            log.error("Athlete or Competition not found.");
        }

        if (registrationRepository.existsByAthleteIdAndCompetitionId(athleteId, competitionId)) {
            log.error("Athlete already registered for this competition.");
        }

        Registration registration = new Registration();
        registration.setAthlete(athlete);
        registration.setCompetition(competition);
        registration.setRegistrationDate(LocalDate.now());

        return registrationRepository.save(registration);
    }

    /**
     * List registered Athletes for a Competition
     * 
     * @param competitionId
     * @return A list of {@link Athlete}
     */
    public List<Athlete> findRegisteredAthletes(Long competitionId) {
        List<Long> athletesID = registrationRepository.findAthleteIdByCompetitionId(competitionId);

        Iterable<Athlete> athletesIterable = athleteRepository.findAllById(athletesID);
        List<Athlete> athletesList = StreamSupport.stream(athletesIterable.spliterator(), false)
                .collect(Collectors.toList());

        return athletesList;
    }

    /**
     * Delete a Registration by id
     */
    public void deleteRegistrationById(Long id) {
        registrationRepository.deleteById(id);
    }

    /**
     * Find a Registration using id_athlete and id_competition
     */
    public Registration findByIdAthleteAndIdCompetition(Long id_athlete, Long id_competition) {

        Athlete athlete = athleteRepository.findById(id_athlete).orElse(null);
        Competition competition = competitionRepository.findById(id_competition).orElse(null);

        return registrationRepository.findByAthleteAndCompetition(athlete, competition);
    }

}