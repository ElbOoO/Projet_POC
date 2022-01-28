package fr.telecom.Poc.Controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.telecom.Poc.DTO.PersonneDTO;
import fr.telecom.Poc.Models.Personne;
import fr.telecom.Poc.Payloads.Requests.NouvellePersonneRequest;
import fr.telecom.Poc.Payloads.Requests.PatchPersonneRequest;
import fr.telecom.Poc.Repositories.PersonneRepository;
import fr.telecom.Poc.Services.ServicesImpl.PersonneServiceImpl;
import fr.telecom.Poc.Utils.ListeRoles;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/personnes")
public class PersonneController {

	@Autowired
	private PersonneRepository personneRepo;

	@Autowired
	private PersonneServiceImpl personneService;

	@Autowired
	PasswordEncoder encoder;

	@GetMapping(produces = "application/json")
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

	@GetMapping(path = "/managers", produces = "application/json")
	public @ResponseBody Iterable<PersonneDTO> getAllManagers() {
		ArrayList<PersonneDTO> result = new ArrayList<PersonneDTO>();

		this.personneService.findAllManagers().forEach(manager -> result.add(new PersonneDTO(manager)));

		return result;
	}

	@PostMapping()
	@PreAuthorize("hasRole('Manager') or hasRole('Admin')")
	public @ResponseBody String addNewPersonne(@RequestBody NouvellePersonneRequest nouvellePersonne) {
		Personne p = new Personne(nouvellePersonne.getNom(), nouvellePersonne.getPrenom(),
				encoder.encode(nouvellePersonne.getPassword()), nouvellePersonne.getRole());

		if (nouvellePersonne.getManager() != null) {
			p.setManager(this.personneService.findPersonne(nouvellePersonne.getManager()).get());
		}

		if (!ListeRoles.isPresent(nouvellePersonne.getRole())) {
			return "Erreur : Ce rôle n'est pas valide";
		}

		try {
			this.personneRepo.save(p);
		} catch (DataIntegrityViolationException e) {
			return "Erreur : l'utilisateur " + nouvellePersonne.getPrenom() + " " + nouvellePersonne.getNom()
					+ " existe deja.";
		}
		return "Nouvel utilisateur sauvegarde !";
	}

	@PatchMapping()
	@PreAuthorize("hasRole('Manager') or hasRole('Admin')")
	public @ResponseBody PersonneDTO patchPersonne(@RequestBody PatchPersonneRequest patchPersonne) {

		Optional<Personne> p = personneService.findPersonne(patchPersonne.getId());

		if (p.isEmpty()) {
			// L'id est invalide
			System.out.println("Erreur : Impossible de moifier l'utilisateur " + patchPersonne.getId()
					+ " car il est introuvable.");
			return null;
		}

		Personne patched = p.get();

		if (patchPersonne.getNom() != null) {
			patched.setNom(patchPersonne.getNom());
		}
		if (patchPersonne.getPrenom() != null) {
			patched.setPrenom(patchPersonne.getPrenom());
		}
		if (patchPersonne.getPassword() != null) {
			patched.setPassword(encoder.encode(patchPersonne.getPassword()));
		}
		if (patchPersonne.getManager() != null) {
			Optional<Personne> manager = this.personneService.findPersonne(patchPersonne.getManager());

			if (manager.isEmpty()) {
				// Le manager n'existe pas
				System.out.println("Erreur : Aucun utilisateur avec l'id " + patchPersonne.getManager());
				return null;
			} else if (!manager.get().getRole().equals("ROLE_Manager")) {
				// Cette personne n'est pas manager
				System.out.println("Erreur : Cet utilisateur n'est pas manager.");
				return null;
			}

			patched.setManager(manager.get());
		}
		if (patchPersonne.getRole() != null) {
			if (ListeRoles.isPresent(patchPersonne.getRole())) {
				patched.setRole(patchPersonne.getRole());
			} else {
				// Le nouveau role est invalide
				return null;
			}
		}

		// personneRepo.delete(p.get());
		personneRepo.save(patched);

		return new PersonneDTO(patched);
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
