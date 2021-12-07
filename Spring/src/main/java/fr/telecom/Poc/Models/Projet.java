package fr.telecom.Poc.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Projet {
	@Id
	@GeneratedValue
	private Integer id;

	private String nom;

	public Projet(String nom) {
		this.nom = nom;
	}
}
