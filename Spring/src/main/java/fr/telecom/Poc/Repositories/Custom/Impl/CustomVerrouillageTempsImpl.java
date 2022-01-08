package fr.telecom.Poc.Repositories.Custom.Impl;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import fr.telecom.Poc.Models.Personne;
import fr.telecom.Poc.Models.VerrouillageTemps;
import fr.telecom.Poc.Repositories.Custom.CustomVerrouillageTempsRepository;

@Transactional(readOnly = true)
public class CustomVerrouillageTempsImpl implements CustomVerrouillageTempsRepository {

	@PersistenceContext
	EntityManager entityManager;
/*
	@Override
	public Optional<VerrouillageTemps> findByDateAndUser(Integer mois, Integer annee, Integer utilisateurId) {
		Query query = entityManager
				.createQuery("FROM Verrou WHERE mois = :mois AND annee = :annee AND utilisateur = :utilisateur_id")
				.setParameter("mois", mois).setParameter("annee", annee).setParameter("utilisateur_id", utilisateurId);

		return Optional.of((VerrouillageTemps) query.getSingleResult());
	}
*/
}
