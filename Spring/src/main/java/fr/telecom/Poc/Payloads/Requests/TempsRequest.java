package fr.telecom.Poc.Payloads.Requests;

import java.time.LocalDate;

/**
 * Payload pour la requete d'ajout de nouveaux temps (demande la date et le
 * poids du temps, l'utilisateur et le projet auxquels il est lie)
 */
public class TempsRequest {
	private LocalDate date;
	private Double poids;
	private Integer utilisateur;
	private Integer projet;

	public TempsRequest(LocalDate date, Double poids, Integer utilisateur, Integer projet) {
		this.date = date;
		this.poids = poids;
		this.utilisateur = utilisateur;
		this.projet = projet;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Double getPoids() {
		return poids;
	}

	public void setPoids(Double poids) {
		this.poids = poids;
	}

	public Integer getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Integer utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Integer getProjet() {
		return projet;
	}

	public void setProjet(Integer projet) {
		this.projet = projet;
	}

}
