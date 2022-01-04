package fr.telecom.Poc.Repositories.Custom.Impl;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.telecom.Poc.Models.Personne;
import fr.telecom.Poc.Repositories.Custom.CustomPersonneRepository;

//@Entity
//@Repository
@Transactional(readOnly = true)
public class CustomPersonneRepositoryImpl implements CustomPersonneRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Optional<Personne> findByUsername(String username) {
		String[] name = username.split("\\.");

		if (name.length != 2) {
			System.out.println("Erreur : format du nom d'utilisateur invalide.");
			return null;
		}

		Query query = entityManager.createQuery("FROM Personne WHERE nom LIKE :nom AND prenom LIKE :prenom")
				.setParameter("nom", name[1]).setParameter("prenom", name[0]);

		return Optional.of((Personne) query.getSingleResult());
	}

}
