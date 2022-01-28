package fr.telecom.Poc.Repositories.Custom;

import java.util.ArrayList;
import java.util.Optional;

import fr.telecom.Poc.Models.Personne;

public interface CustomPersonneRepository {
	public Optional<Personne> findByUsername(String username);

	public ArrayList<Personne> findManagers();
}
