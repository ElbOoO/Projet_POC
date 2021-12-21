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
import fr.telecom.Poc.Repositories.PersonneRepository;
import fr.telecom.Poc.Services.ServicesImpl.PersonneServiceImpl;

@Controller
@RequestMapping(path = "/personnes")
public class PersonneController {

	@Autowired
	private PersonneRepository personneRepo;

	@Autowired
	private PersonneServiceImpl personneService;

	@GetMapping(produces = "application/json")
	public @ResponseBody Iterable<Personne> getAllPersonnes() {
		return this.personneService.findAllPersonnes();
	}

	@GetMapping(path = "/{id}", produces = "application/json")
	@ResponseBody
	public Personne getPersonneById(@PathVariable Integer id) {
		Optional<Personne> p = this.personneService.findPersonne(id);

		if (!p.isEmpty()) {
			return p.get();
		} else {
			return null;
		}
	}

	@GetMapping(path = "/manager={id}", produces = "application/json")
	public @ResponseBody Iterable<Personne> getPersonnesByManager(@PathVariable Integer id) {
		Optional<Personne> manager = this.personneService.findPersonne(id);

		if (!manager.isEmpty()) {
			return this.personneService.findPersonneByManager(manager.get());
		} else {
			return null;
		}
	}

	@PostMapping()
	public @ResponseBody String addNewPersonne(@RequestParam String nom, @RequestParam String prenom,
			@RequestParam String password, @RequestParam String role,
			@RequestParam(required = false) Integer managerId) {
		Personne p = new Personne(nom, prenom, password, role);
		if (managerId != null) {
			p.setManager(this.personneService.findPersonne(managerId).get());
		}

		this.personneRepo.save(p);
		return "Saved";
	}
}