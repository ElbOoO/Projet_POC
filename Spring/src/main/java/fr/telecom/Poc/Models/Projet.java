package fr.telecom.Poc.Models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Classe qui represente les projets qui sont rentres dans l'application
 */
@Entity
public class Projet {
	@Id
	@GeneratedValue
	private Integer id;

	private String nom;

	private String couleur;

	@ManyToOne
	private Personne manager;

	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "projet", fetch = FetchType.LAZY)
	private Set<Temps> temps;

	public Projet() {

	}

	public Projet(String nom, String couleur, Personne manager) {
		this.nom = nom;
		this.couleur = couleur;
		this.manager = manager;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Personne getManager() {
		return manager;
	}

	public void setManager(Personne manager) {
		this.manager = manager;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
}
