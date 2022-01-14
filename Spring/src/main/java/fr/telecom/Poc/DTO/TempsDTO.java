package fr.telecom.Poc.DTO;

import java.sql.Date;
import fr.telecom.Poc.Models.Temps;

public class TempsDTO {

	private Integer id;
	private Date date;
	private Double poids;
	private Integer utilisateur;
	private Integer projet;

	public TempsDTO(Date date, Double poids, Integer utilisateur, Integer projet) {
		this.date = date;
		this.poids = poids;
		this.utilisateur = utilisateur;
		this.projet = projet;
	}

	public TempsDTO(Temps t) {
		this.id = t.getId();
		this.date = t.getDate();
		this.poids = t.getPoids();
		this.utilisateur = t.getUtilisateur().getId();
		this.projet = t.getProjet().getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
