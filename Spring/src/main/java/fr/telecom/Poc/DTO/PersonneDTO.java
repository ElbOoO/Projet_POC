package fr.telecom.Poc.DTO;

import fr.telecom.Poc.Models.Personne;

public class PersonneDTO {

	private Integer id;
	private String prenom;
	private String nom;
	private String role;
	private Integer manager;

	public PersonneDTO(Personne p) {
		this.id = p.getId();
		this.nom = p.getNom();
		this.prenom = p.getPrenom();
		this.role = p.getRole();

		if (p.getManager() == null) {
			this.manager = null;
		} else {
			this.manager = p.getManager().getId();
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getManager() {
		return manager;
	}

	public void setManager(Integer manager) {
		this.manager = manager;
	}

}
