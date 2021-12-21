package fr.telecom.Poc.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.telecom.Poc.Models.Personne;
import fr.telecom.Poc.Models.Projet;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Integer> {
	public List<Projet> findByManager(Personne manager);

}
