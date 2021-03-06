package fr.telecom.Poc.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import fr.telecom.Poc.Models.Personne;
import fr.telecom.Poc.Models.Temps;

public interface TempsService {
	public List<Temps> findAllTemps();

	public Optional<Temps> findTemps(Integer id);

	public List<Temps> findByUtilisateur(Personne utilisateur);

	public List<Temps> exportTempsUtilisateur(Personne utilisateur, LocalDate date);
}
