package fr.telecom.Poc.Repositories.Custom;

import java.sql.Date;
import java.util.List;

import fr.telecom.Poc.Models.Temps;

public interface CustomTempsRepository {
	public List<Temps> findByUtilisateurForMonth(Integer userId, Date date);
}
