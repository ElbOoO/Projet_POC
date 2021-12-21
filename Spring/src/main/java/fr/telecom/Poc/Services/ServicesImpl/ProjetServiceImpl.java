package fr.telecom.Poc.Services.ServicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.telecom.Poc.Models.Personne;
import fr.telecom.Poc.Models.Projet;
import fr.telecom.Poc.Repositories.ProjetRepository;
import fr.telecom.Poc.Services.ProjetService;

@Service
public class ProjetServiceImpl implements ProjetService {

	@Autowired
	ProjetRepository projetRepo;

	@Override
	public List<Projet> findAllProjets() {
		return projetRepo.findAll();
	}

	@Override
	public Optional<Projet> findProjet(Integer id) {
		return projetRepo.findById(id);
	}

	@Override
	public List<Projet> findProjetByManager(Personne manager) {
		return this.projetRepo.findByManager(manager);
	}

}
