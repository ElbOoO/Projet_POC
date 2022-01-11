package fr.telecom.Poc.Models;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;

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

	public Temps() {

	}

	public Temps(Date date, Double poids, Personne utilisateur, Projet projet) {
		this.date = date;
		this.poids = poids;
		this.utilisateur = utilisateur;
		this.projet = projet;
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

}
