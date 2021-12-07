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

	public Temps(Date date, Double poids, Personne utilisateur, Projet projet) {
		this.date = date;
		this.poids = poids;
		this.utilisateur = utilisateur;
		this.projet = projet;
	}
}
