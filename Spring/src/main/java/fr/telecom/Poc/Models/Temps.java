package fr.telecom.Poc.Models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Temps {
	@Id
	@GeneratedValue
	private Integer id;

	private Date date;
	private Double poids;
	@ManyToOne
	private Personne utilisateur;
	@ManyToOne
	private Projet projet;

	private Boolean locked;

	public Temps() {

	}

	public Temps(Date date, Double poids, Personne utilisateur, Projet projet) {
		this.date = date;
		this.poids = poids;
		this.utilisateur = utilisateur;
		this.projet = projet;
		this.locked = false;
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

	public Personne getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Personne utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

}
