package fr.telecom.Poc.Payloads.Requests;

public class NouvellePersonneRequest {
	private String nom;
	private String prenom;
	private String password;
	private String role;
	private Integer manager;

	public NouvellePersonneRequest(String nom, String prenom, String password, String role) {
		this.nom = nom;
		this.prenom = prenom;
		this.password = password;
		this.role = role;
	}

	public NouvellePersonneRequest(String nom, String prenom, String password, String role, Integer manager) {
		this.nom = nom;
		this.prenom = prenom;
		this.password = password;
		this.role = role;
		this.manager = manager;
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

	public Integer getManager() {
		return manager;
	}

	public void setManager(Integer manager) {
		this.manager = manager;
	}

}
