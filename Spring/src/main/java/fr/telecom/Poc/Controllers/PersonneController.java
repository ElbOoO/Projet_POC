package fr.telecom.Poc.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.telecom.Poc.Models.Personne;
import fr.telecom.Poc.Repositories.PersonneRepository;

@Controller
@RequestMapping(path = "/personne")
public class PersonneController {

	@Autowired
	private PersonneRepository personneRepo;
	
	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Personne> getAllPersonnes() {
		System.out.println(personneRepo.findAll());
		return personneRepo.findAll();
	}
	
	@PostMapping(path = "/add")
	public @ResponseBody String addNewPersonne(@RequestParam String nom) {
		Personne p = new Personne(nom);
		personneRepo.save(p);
		return "Saved";
	}
}
