package fr.telecom.Poc.Repositories.Custom.Impl;

import java.util.ArrayList;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

		// On force le format du nom et du prenom (majuscule au debut puis tout en
		// minuscules)
		String prenom = name[0].substring(0, 1).toUpperCase() + name[0].substring(1).toLowerCase();
		String nom = name[1].substring(0, 1).toUpperCase() + name[1].substring(1).toLowerCase();

		Query query = entityManager.createQuery("FROM Personne WHERE nom LIKE :nom AND prenom LIKE :prenom")
				.setParameter("nom", nom).setParameter("prenom", prenom);

		return Optional.of((Personne) query.getSingleResult());
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Personne> findManagers() {
		Query query = entityManager.createQuery("FROM Personne WHERE role LIKE 'ROLE_Manager'");

		return (ArrayList<Personne>) query.getResultList();
	}

}
