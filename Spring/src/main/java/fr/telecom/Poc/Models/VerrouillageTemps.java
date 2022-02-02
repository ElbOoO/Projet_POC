package fr.telecom.Poc.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Classe qui represente les mois deja exportes pour un utilisateur
 */
@Entity
@Table(name = "Verrou")
public class VerrouillageTemps {

	@Id
	@GeneratedValue
	private Integer id;

	private Integer mois;
	private Integer annee;

	@ManyToOne
	private Personne utilisateur;

	public VerrouillageTemps() {

	}

	public VerrouillageTemps(Integer mois, Integer annee, Personne utilisateur) {
		super();
		this.mois = mois;
		this.annee = annee;
		this.utilisateur = utilisateur;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMois() {
		return mois;
	}

	public void setMois(Integer mois) {
		this.mois = mois;
	}

	public Integer getAnnee() {
		return annee;
	}

	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

	public Personne getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Personne utilisateur) {
		this.utilisateur = utilisateur;
	}

}
