package fr.telecom.Poc.Models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Classe qui represente les utilisateur de l'application
 */
@Entity
@Table(name = "Personne", uniqueConstraints = {
		@UniqueConstraint(name = "UniqueUsername", columnNames = { "nom", "prenom" }) })
public class Personne {
	@Id
	@GeneratedValue
	private Integer id;
	private String nom;
	private String prenom;
	private String password;
	private String role;

	@ManyToOne
	private Personne manager;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "utilisateur", fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<VerrouillageTemps> verrous;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "utilisateur", fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<Temps> temps;

	@OneToMany(cascade = CascadeType.DETACH, mappedBy = "manager", fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<Projet> projets;

	public Personne() {
	}

	public Personne(String nom) {
		this.nom = nom;
	}

	public Personne(String nom, String prenom, String password, String role) {
		this.nom = nom;
		this.prenom = prenom;
		this.password = password;
		this.role = role;
	}

	public Personne(Integer id, String nom, String prenom, String password, String role) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.password = password;
		this.role = role;
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Personne getManager() {
		return this.manager;
	}

	public void setManager(Personne manager) {
		this.manager = manager;
	}

	public String getUsername() {
		return this.prenom + "." + this.nom;
	}

	@Override
	public String toString() {
		String str = "Personne [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", password=" + password + ", role="
				+ role + "]";
		return str;
	}

}
