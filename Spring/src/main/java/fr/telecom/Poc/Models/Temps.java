package fr.telecom.Poc.Models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Classe qui represente les temps des utilisateurs
 */
@Entity
public class Temps {
	@Id
	@GeneratedValue
	private Integer id;

	private LocalDate date;
	private Double poids;
	@ManyToOne
	private Personne utilisateur;
	@ManyToOne
	private Projet projet;

	private Boolean locked;

	public Temps() {

	}

	public Temps(LocalDate date, Double poids, Personne utilisateur, Projet projet) {
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
