package fr.telecom.Poc.Controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.telecom.Poc.DTO.PersonneDTO;
import fr.telecom.Poc.Models.Personne;
import fr.telecom.Poc.Payloads.Requests.NouvellePersonneRequest;
import fr.telecom.Poc.Repositories.PersonneRepository;
import fr.telecom.Poc.Services.ServicesImpl.PersonneServiceImpl;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/personnes")
public class PersonneController {

	@Autowired
	private PersonneRepository personneRepo;

	@Autowired
	private PersonneServiceImpl personneService;

	@GetMapping(produces = "application/json")
	@PreAuthorize("hasRole('Manager') or hasRole('Admin')")
	public @ResponseBody Iterable<PersonneDTO> getAllPersonnes() {
		ArrayList<PersonneDTO> result = new ArrayList<PersonneDTO>();

		this.personneService.findAllPersonnes().forEach(p -> result.add(new PersonneDTO(p)));

		return result;
	}

	@GetMapping(path = "/{id}", produces = "application/json")
	@ResponseBody
	public PersonneDTO getPersonneById(@PathVariable Integer id) {
		Optional<Personne> p = this.personneService.findPersonne(id);

		if (!p.isEmpty()) {
			return new PersonneDTO(p.get());
		} else {
			return null;
		}
	}

	@GetMapping(path = "/manager={id}", produces = "application/json")
	@PreAuthorize("hasRole('Manager') or hasRole('Admin')")
	public @ResponseBody Iterable<PersonneDTO> getPersonnesByManager(@PathVariable Integer id) {
		Optional<Personne> manager = this.personneService.findPersonne(id);
		ArrayList<PersonneDTO> result = new ArrayList<PersonneDTO>();

		if (!manager.isEmpty()) {
			this.personneService.findPersonneByManager(manager.get()).forEach(p -> result.add(new PersonneDTO(p)));
			return result;
		} else {
			return null;
		}
	}

	@PostMapping()
	@PreAuthorize("hasRole('Manager') or hasRole('Admin')")
	public @ResponseBody String addNewPersonne(@RequestBody NouvellePersonneRequest nouvellePersonne) {
		Personne p = new Personne(nouvellePersonne.getNom(), nouvellePersonne.getPrenom(),
				nouvellePersonne.getPassword(), nouvellePersonne.getRole());
		if (nouvellePersonne.getManager() != null) {
			p.setManager(this.personneService.findPersonne(nouvellePersonne.getManager()).get());
		}

		try {
			this.personneRepo.save(p);
		} catch (DataIntegrityViolationException e) {
			return "This username is already taken";
		}
		return "Saved";
	}

	@DeleteMapping(path = "/{id}")
	@PreAuthorize("hasRole('Admin')")
	@ResponseBody
	public String deletePersonne(@PathVariable Integer id) {
		if (this.personneService.findPersonne(id).isPresent()) {
			this.personneRepo.deleteById(id);
			return "Personne supprimée";
		} else {
			return "Aucun utilisateur trouvé pour l'id " + id;
		}
	}
}
