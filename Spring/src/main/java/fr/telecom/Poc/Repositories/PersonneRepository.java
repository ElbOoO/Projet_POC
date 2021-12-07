package fr.telecom.Poc.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.telecom.Poc.Models.Personne;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Integer> {

}
