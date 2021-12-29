package fr.telecom.Poc.Controllers;

import java.sql.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.telecom.Poc.Models.Personne;
import fr.telecom.Poc.Models.Projet;
import fr.telecom.Poc.Models.Temps;
import fr.telecom.Poc.Repositories.TempsRepository;
import fr.telecom.Poc.Services.ServicesImpl.PersonneServiceImpl;
import fr.telecom.Poc.Services.ServicesImpl.ProjetServiceImpl;
import fr.telecom.Poc.Services.ServicesImpl.TempsServiceImpl;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/temps")
public class TempsController {
	@Autowired
	TempsRepository tempsRepo;
	@Autowired
	TempsServiceImpl tempsService;
	@Autowired
	PersonneServiceImpl personneService;
	@Autowired
	ProjetServiceImpl projetService;

	@GetMapping(produces = "application/json")
	@ResponseBody
	public Iterable<Temps> getAllTemps() {
		return this.tempsService.findAllTemps();
	}

	@GetMapping(path = "/{id}", produces = "application/json")
	@ResponseBody
	public Temps getTempsById(@PathVariable Integer id) {
		Optional<Temps> t = this.tempsService.findTemps(id);

		if (!t.isEmpty()) {
			return t.get();
		} else {
			return null;
		}
	}

	@GetMapping(path = "/utilisateur={userId}", produces = "application/json")
	@ResponseBody
	public Iterable<Temps> getTempsByPersonne(@PathVariable Integer userId) {
		Optional<Personne> utilisateur = this.personneService.findPersonne(userId);

		if (!utilisateur.isEmpty()) {
			return this.tempsService.findByUtilisateur(utilisateur.get());
		} else {
			return null;
		}
	}

	@PostMapping
	@ResponseBody
	public String addTemps(@RequestParam Date date, @RequestParam Double poids, @RequestParam Integer utilisateurId,
			@RequestParam Integer projetId) {
		Optional<Personne> utilisateur = this.personneService.findPersonne(utilisateurId);
		Optional<Projet> projet = this.projetService.findProjet(projetId);

		if (!projet.isEmpty() && !utilisateur.isEmpty()) {
			this.tempsRepo.save(new Temps(date, poids, utilisateur.get(), projet.get()));
			return "Saved.";
		} else if (projet.isEmpty()) {
			return "Error : Cannot find a Projet with the id: " + projetId;
		} else {
			return "Error : Cannot find a Personne with the id: " + utilisateurId;
		}
	}
}
