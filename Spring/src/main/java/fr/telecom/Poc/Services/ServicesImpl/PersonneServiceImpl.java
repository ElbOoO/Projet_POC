package fr.telecom.Poc.Services.ServicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.telecom.Poc.Models.Personne;
import fr.telecom.Poc.Repositories.PersonneRepository;
import fr.telecom.Poc.Services.PersonneService;

@Service
public class PersonneServiceImpl implements PersonneService {

	@Autowired
	PersonneRepository personneRepo;

	@Override
	public List<Personne> findAllPersonnes() {
		return personneRepo.findAll();
	}

	@Override
	public List<Personne> findPersonneByManager(Personne manager) {
		return personneRepo.findByManager(manager);
	}

	@Override
	public Optional<Personne> findPersonne(Integer id) {
		return personneRepo.findById(id);
	}

	@Override
	public Optional<Personne> findPersonneByUsername(String username) {
		return this.personneRepo.findByUsername(username);
	}

	@Override
	public ArrayList<Personne> findAllManagers() {
		return this.personneRepo.findManagers();
	}

}
