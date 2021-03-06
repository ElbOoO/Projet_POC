package fr.telecom.Poc.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.telecom.Poc.Models.Personne;
import fr.telecom.Poc.Models.VerrouillageTemps;

@Repository
public interface VerrouillageTempsRepository extends JpaRepository<VerrouillageTemps, Integer> {
	public Optional<VerrouillageTemps> findByMoisAndAnneeAndUtilisateur(Integer mois, Integer annee,
			Personne utilisateur);
}
