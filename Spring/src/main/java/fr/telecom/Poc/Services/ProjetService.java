package fr.telecom.Poc.Services;

import java.util.List;
import java.util.Optional;

import fr.telecom.Poc.Models.Projet;

public interface ProjetService {
	public List<Projet> findAllProjets();
	
	public Optional<Projet> findProjet(Integer id);
}
