package fr.telecom.Poc.Payloads.Requests;

public class SignupRequest {

	private String prenom;
	private String nom;
	private String role;
	private String password;

	/*
	 * public SignupRequest() { this.role = new ArrayList<String>(); }
	 */

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}