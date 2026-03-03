package fr.ensai.running.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ensai.running.model.Athlete;
import fr.ensai.running.repository.AthleteRepository;

@Service
public class AthleteService {

    private static final Logger log = LoggerFactory.getLogger(AthleteService.class);

    @Autowired
    private AthleteRepository athleteRepository;

    /**
     * Find an Athlete by id
     * 
     * @return Athlete or null if not found
     */
    public Athlete findById(Long id) {
        return athleteRepository.findById(id).orElse(null);
    }

    /**
     * List of all Athletes
     */
    public List<Athlete> findAll() {
        return athleteRepository.findAll();
    }

    /**
     * Create or Update an Athlete
     */
    public Athlete save(Athlete athlete) {
        return athleteRepository.save(athlete);
    }

    /**
     * Delete an Athlete by id
     */
    public void deleteById(Long id) {
        athleteRepository.deleteById(id);
        log.warn("Athlete {} deleted", id);
    }
}