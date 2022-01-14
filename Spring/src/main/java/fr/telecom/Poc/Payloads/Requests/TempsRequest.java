package fr.telecom.Poc.Payloads.Requests;

import java.sql.Date;

public class TempsRequest {
	private Date date;
	private Double poids;
	private Integer utilisateur;
	private Integer projet;

	public TempsRequest(Date date, Double poids, Integer utilisateur, Integer projet) {
		this.date = date;
		this.poids = poids;
		this.utilisateur = utilisateur;
		this.projet = projet;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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