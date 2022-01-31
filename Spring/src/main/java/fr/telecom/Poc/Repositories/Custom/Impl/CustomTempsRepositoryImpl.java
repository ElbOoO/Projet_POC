package fr.telecom.Poc.Repositories.Custom.Impl;

import java.sql.Date;
import java.time.LocalDate;
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

	@Override
	public List<Temps> findByUtilisateurForMonth(Integer userId, LocalDate date) {
		System.out.println("Find temps by month:");

		Calendar cal = Calendar.getInstance();
		cal.set(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());

		System.out.println("original date: " + date);
		System.out.println("original calendar: " + cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + "-"
				+ cal.get(Calendar.DAY_OF_MONTH));

		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date dateDebut = new Date(cal.getTime().getTime());

		System.out.println("start date: " + dateDebut);
		System.out.println("start calendar: " + cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + "-"
				+ cal.get(Calendar.DAY_OF_MONTH));

		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date dateFin = new Date(cal.getTime().getTime());

		System.out.println("end date: " + dateFin);
		System.out.println("end calendar: " + cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + "-"
				+ cal.get(Calendar.DAY_OF_MONTH));

		Query query = entityManager
				.createQuery("FROM Temps WHERE utilisateur_id = :id AND date >= :debut AND date <= :fin")
				.setParameter("id", userId).setParameter("debut", dateDebut).setParameter("fin", dateFin);

		return query.getResultList();
	}

}
