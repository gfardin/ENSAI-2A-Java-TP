package fr.ensai.running.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.ensai.running.model.Registration;
import fr.ensai.running.service.CompetitionService;

@Controller
@RequestMapping("/competition")
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;

    /**
     * List all Competitions
     */
    @GetMapping()
    public String findAllCompetitions(Model model) {
        model.addAttribute("competitions", competitionService.findAll());
        return "allCompetitions";
    }

    /**
     * Delete a Competition by id
     */
    @GetMapping("/delete/{id}")
    public String deleteCompetitionById(@PathVariable(value = "id") long id) {
        competitionService.deleteById(id);
        return "redirect:/competition";

    }

    /**
     * Delete a Registration
     * 
     * @param competitionId
     * @param athleteId
     */
    @GetMapping("/delete/{id_competition}/athlete/{id_athlete}")
    public String deleteRegistration(
            @PathVariable("id_competition") Long competitionId,
            @PathVariable("id_athlete") Long athleteId) {

        Registration registration = competitionService.findByIdAthleteAndIdCompetition(athleteId, competitionId);
        competitionService.deleteRegistrationById(registration.getId());

        return "redirect:/competition/{id_competition}/athletes";

    }

    /**
     * Register an Athlete to a Competition
     */
    @GetMapping("/{id_competition}/athlete/{id_athlete}")
    public String registerAthleteCompetition(
            @PathVariable("id_competition") Long competitionId,
            @PathVariable("id_athlete") Long athleteId) {

        competitionService.registerAthlete(athleteId, competitionId);
        return "redirect:/competition";
    }

    /**
     * List registered Athletes for a Competition
     */
    @GetMapping("/{id}/athletes")
    public String registeredAthletes(@PathVariable(value = "id") long id, Model model) {

        model.addAttribute("competition", competitionService.findById(id));
        model.addAttribute("athletes", competitionService.findRegisteredAthletes(id));
        return "competitionAthletes";
    }

}