package fr.telecom.Poc.Repositories.Custom.Impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.telecom.Poc.Models.Temps;
import fr.telecom.Poc.Repositories.Custom.CustomTempsRepository;

public class CustomTempsRepositoryImpl implements CustomTempsRepository {

	@PersistenceContext
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Temps> findByUtilisateurForMonth(Integer userId, LocalDate date) {
		Calendar cal = Calendar.getInstance();
		cal.set(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());

		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		LocalDate dateDebut = LocalDateTime.ofInstant(cal.toInstant(), cal.getTimeZone().toZoneId()).toLocalDate();

		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		LocalDate dateFin = LocalDateTime.ofInstant(cal.toInstant(), cal.getTimeZone().toZoneId()).toLocalDate();

		Query query = entityManager
				.createQuery("FROM Temps WHERE utilisateur_id = :id AND date >= :debut AND date <= :fin")
				.setParameter("id", userId).setParameter("debut", dateDebut).setParameter("fin", dateFin);

		return query.getResultList();
	}

}
