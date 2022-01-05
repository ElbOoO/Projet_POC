package fr.telecom.Poc.Controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.telecom.Poc.DTO.ProjetDTO;
import fr.telecom.Poc.Models.Personne;
import fr.telecom.Poc.Models.Projet;
import fr.telecom.Poc.Repositories.ProjetRepository;
import fr.telecom.Poc.Services.ServicesImpl.PersonneServiceImpl;
import fr.telecom.Poc.Services.ServicesImpl.ProjetServiceImpl;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
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
	public Iterable<ProjetDTO> getAllProjets() {
		ArrayList<ProjetDTO> result = new ArrayList<ProjetDTO>();

		this.projetService.findAllProjets().forEach(p -> result.add(new ProjetDTO(p)));

		return result;
	}

	@GetMapping(path = "/{id}", produces = "application/json")
	@ResponseBody
	public ProjetDTO getProjectById(@PathVariable Integer id) {
		Optional<Projet> p = this.projetService.findProjet(id);

		if (!p.isEmpty()) {
			return new ProjetDTO(p.get());
		} else {
			return null;
		}
	}

	@GetMapping(path = "/manager={managerId}", produces = "application/json")
	@ResponseBody
	public Iterable<ProjetDTO> getProjetsByManager(@PathVariable Integer managerId) {
		ArrayList<ProjetDTO> result = new ArrayList<ProjetDTO>();
		Optional<Personne> manager = this.personneService.findPersonne(managerId);

		if (!manager.isEmpty()) {
			this.projetService.findProjetByManager(manager.get()).forEach(p -> result.add(new ProjetDTO(p)));
			return result;
		} else {
			return null;
		}
	}

	@PostMapping
	@PreAuthorize("hasRole('Manager') or hasRole('Admin')")
	@ResponseBody
	public String addProjet(@RequestParam String nom, @RequestParam String couleur, @RequestParam Integer managerId) {
		Optional<Personne> manager = this.personneService.findPersonne(managerId);

		if (manager.isEmpty()) {
			return "Error : this manager does not exist.";
		} else {
			this.projetRepo.save(new Projet(nom, couleur, manager.get()));
			return "Saved.";
		}
	}

	@DeleteMapping(path = "/{id}")
	@PreAuthorize("hasRole('Manager') or hasRole('Admin')")
	@ResponseBody
	public String deleteProjet(@PathVariable Integer id) {
		if (this.projetService.findProjet(id).isPresent()) {
			this.projetRepo.deleteById(id);
			return "Projet supprimé";
		} else {
			return "Aucun projet trouvé pour l'id " + id;
		}
	}
}
