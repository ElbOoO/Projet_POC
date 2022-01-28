package fr.telecom.Poc.Payloads.Requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PatchPersonneRequest {

	@NotNull(message = "Erreur : Il faut obligatoirement un id.")
	@NotBlank(message = "Erreur : Un id ne peut pas être vide.")
	private Integer id;
	private String nom;
	private String prenom;
	private String role;
	private String password;
	private Integer manager;

	public PatchPersonneRequest() {

	}

	public PatchPersonneRequest(Integer id, String nom, String prenom, String role, String password) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.role = role;
		this.password = password;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getManager() {
		return manager;
	}

	public void setManager(Integer manager) {
		this.manager = manager;
	}

}
