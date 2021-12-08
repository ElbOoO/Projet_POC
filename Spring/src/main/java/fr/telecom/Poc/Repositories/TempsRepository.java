package fr.telecom.Poc.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.telecom.Poc.Models.Personne;
import fr.telecom.Poc.Models.Temps;

@Repository
public interface TempsRepository extends JpaRepository<Temps, Integer> {
	public List<Temps> findByUtilisateur(Personne utilisateur);
}
