package fr.telecom.Poc.Controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.telecom.Poc.DTO.TempsDTO;
import fr.telecom.Poc.Models.Personne;
import fr.telecom.Poc.Models.Projet;
import fr.telecom.Poc.Models.Temps;
import fr.telecom.Poc.Models.VerrouillageTemps;
import fr.telecom.Poc.Repositories.TempsRepository;
import fr.telecom.Poc.Repositories.VerrouillageTempsRepository;
import fr.telecom.Poc.Services.ServicesImpl.PersonneServiceImpl;
import fr.telecom.Poc.Services.ServicesImpl.ProjetServiceImpl;
import fr.telecom.Poc.Services.ServicesImpl.TempsServiceImpl;
import fr.telecom.Poc.Services.ServicesImpl.VerrouillageTempsServiceImpl;

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
	@Autowired
	VerrouillageTempsRepository verrouRepo;
	@Autowired
	VerrouillageTempsServiceImpl verrouService;

	@GetMapping(produces = "application/json")
	@ResponseBody
	public Iterable<Temps> getAllTemps() {
		return this.tempsService.findAllTemps();
	}

	@GetMapping(path = "/{id}", produces = "application/json")
	@ResponseBody
	public TempsDTO getTempsById(@PathVariable Integer id) {
		Optional<Temps> t = this.tempsService.findTemps(id);

		if (!t.isEmpty()) {
			return new TempsDTO(t.get());
		} else {
			return null;
		}
	}

	@GetMapping(path = "/utilisateur={userId}", produces = "application/json")
	@ResponseBody
	public Iterable<TempsDTO> getTempsByPersonne(@PathVariable Integer userId) {
		ArrayList<TempsDTO> result = new ArrayList<TempsDTO>();
		Optional<Personne> utilisateur = this.personneService.findPersonne(userId);

		if (!utilisateur.isEmpty()) {
			this.tempsService.findByUtilisateur(utilisateur.get()).forEach(t -> result.add(new TempsDTO(t)));
			return result;
		} else {
			return null;
		}
	}

	@GetMapping(path = "/export", produces = "application/json")
	@ResponseBody
	public Iterable<TempsDTO> exportTemps(@RequestParam Integer userId, @RequestParam Date date) {
		Optional<Personne> user = personneService.findPersonne(userId);
		ArrayList<TempsDTO> result = new ArrayList<TempsDTO>();

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		if (user.isEmpty()) {
			System.out.println("Erreur : l'utilisateur avec l'id " + userId + " n'existe pas dans la base de donnees.");
			return null;
		}

		try {
			verrouRepo.save(new VerrouillageTemps(cal.get(Calendar.MONTH), cal.get(Calendar.YEAR), user.get()));
			tempsService.exportTempsUtilisateur(user.get(), date).forEach(temps -> result.add(new TempsDTO(temps)));
		} catch (DataIntegrityViolationException e) {
			// Si ce verrou existe deja
			System.out.println("Erreur : Les temps recherches ont deja ete exportes.");
			return null;
		}

		return result;
	}

	@PostMapping
	@ResponseBody
	public String addTemps(@RequestParam Date date, @RequestParam Double poids, @RequestParam Integer utilisateurId,
			@RequestParam Integer projetId) {
		Optional<Personne> utilisateur = this.personneService.findPersonne(utilisateurId);
		Optional<Projet> projet = this.projetService.findProjet(projetId);

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		if (!projet.isEmpty() && !utilisateur.isEmpty()) {

			if (!verrouService.isLocked(cal.get(Calendar.MONTH), cal.get(Calendar.YEAR), utilisateur.get())) {
				this.tempsRepo.save(new Temps(date, poids, utilisateur.get(), projet.get()));
				return "Saved.";
			} else {
				return ("Erreur : Impossible d'ajouter un temps a cette date " + date
						+ " : ce mois a deja ete exporte");
			}

		} else if (projet.isEmpty()) {
			return "Error : Cannot find a Projet with the id: " + projetId;
		} else {
			return "Error : Cannot find a Personne with the id: " + utilisateurId;
		}
	}

	@DeleteMapping(path = "/{id}")
	@ResponseBody
	public String deleteTemps(@PathVariable Integer id) {
		Optional<Temps> t = this.tempsService.findTemps(id);

		if (!t.isEmpty()) {
			Date d = t.get().getDate();

			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			if (!this.verrouService.isLocked(cal.get(Calendar.MONTH), cal.get(Calendar.YEAR),
					t.get().getUtilisateur())) {
				this.tempsRepo.deleteById(id);
				return "Temps supprimé";
			} else {
				return "Erreur : Impossible de supprimer ce temps car il a deja ete exporte.";
			}
		} else {
			return "Erreur : Aucun temps trouvé avec l'id " + id;
		}
	}
}
