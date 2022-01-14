package fr.telecom.Poc.Services;

import java.util.List;

import fr.telecom.Poc.Models.Personne;
import fr.telecom.Poc.Models.VerrouillageTemps;

public interface VerrouillageTempsService {
	public List<VerrouillageTemps> findAllLocks();

	public Boolean isLocked(Integer mois, Integer annee, Personne utilisateur);
}
