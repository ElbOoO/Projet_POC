package fr.telecom.Poc.Models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Personne")
public class Personne {
	@Id
	@GeneratedValue
	private Integer id;

	private String nom;
	private String prenom;
	private String password;
	private String role;

	@ManyToOne(cascade = CascadeType.ALL)
	private Personne managedBy;

	public Personne() {
	}

	public Personne(String nom) {
		this.nom = nom;
	}

	public Personne(String nom, String prenom, String password, String role) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.password = password;
		this.role = role;
	}

	public void addManager(Personne manager) {
		this.managedBy = manager;
	}

	@Override
	public String toString() {
		String str = "Personne [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", password=" + password + ", role="
				+ role + "]";
		return str;
	}

}
