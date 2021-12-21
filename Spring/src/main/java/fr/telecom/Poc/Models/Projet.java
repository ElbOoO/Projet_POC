package fr.telecom.Poc.Models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Projet {
	@Id
	@GeneratedValue
	private Integer id;

	private String nom;

	@ManyToOne(cascade = CascadeType.REFRESH)
	private Personne manager;

	public Projet() {

	}

	public Projet(String nom, Personne manager) {
		this.nom = nom;
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
}
