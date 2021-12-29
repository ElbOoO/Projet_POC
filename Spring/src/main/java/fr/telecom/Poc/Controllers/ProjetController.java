package fr.telecom.Poc.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.telecom.Poc.Models.Personne;
import fr.telecom.Poc.Models.Projet;
import fr.telecom.Poc.Repositories.ProjetRepository;
import fr.telecom.Poc.Services.ServicesImpl.PersonneServiceImpl;
import fr.telecom.Poc.Services.ServicesImpl.ProjetServiceImpl;

@Controller
@RequestMapping(path = "/projets")
public class ProjetController {

	@Autowired
	ProjetRepository projetRepo;
	@Autowired
	ProjetServiceImpl projetService;
	@Autowired
	PersonneServiceImpl personneService;

	@GetMapping(produces = "application/json")
	@ResponseBody
	public Iterable<Projet> getAllProjets() {
		return this.projetService.findAllProjets();
	}

	@GetMapping(path = "/{id}", produces = "application/json")
	@ResponseBody
	public Projet getProjectById(@PathVariable Integer id) {
		Optional<Projet> p = this.projetService.findProjet(id);

		if (!p.isEmpty()) {
			return p.get();
		} else {
			return null;
		}
	}

	@GetMapping(path = "/manager={managerId}", produces = "application/json")
	@ResponseBody
	public Iterable<Projet> getProjetsByManager(@PathVariable Integer managerId) {
		Optional<Personne> manager = this.personneService.findPersonne(managerId);

		if (!manager.isEmpty()) {
			return this.projetService.findProjetByManager(manager.get());
		} else {
			return null;
		}
	}

	@PostMapping
	@ResponseBody
	public String addProjet(@RequestParam String nom, @RequestParam Integer managerId) {
		Optional<Personne> manager = this.personneService.findPersonne(managerId);

		if (manager.isEmpty()) {
			return "Error : this manager does not exist.";
		} else {
			this.projetRepo.save(new Projet(nom, manager.get()));
			return "Saved.";
		}
	}
}
