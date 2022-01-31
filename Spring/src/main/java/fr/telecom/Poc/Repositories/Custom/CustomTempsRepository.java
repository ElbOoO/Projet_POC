package fr.telecom.Poc.Repositories.Custom;

import java.time.LocalDate;
import java.util.List;

import fr.telecom.Poc.Models.Temps;

public interface CustomTempsRepository {
	public List<Temps> findByUtilisateurForMonth(Integer userId, LocalDate date);
}
