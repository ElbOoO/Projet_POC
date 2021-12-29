package fr.telecom.Poc.Services;

import java.util.List;
import java.util.Optional;

import fr.telecom.Poc.Models.Personne;

public interface PersonneService {
	public List<Personne> findAllPersonnes();
	
	public Optional<Personne> findPersonne(Integer id);
	
	public List<Personne> findPersonneByManager(Personne manager);
}
