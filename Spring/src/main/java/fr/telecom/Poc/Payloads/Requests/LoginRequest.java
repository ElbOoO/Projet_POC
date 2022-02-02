package fr.telecom.Poc.Payloads.Requests;

/**
 * Payload pour la requete d'authentification (demande le username et le mot de
 * passe)
 */
public class LoginRequest {

	private String username;
	private String password;

	public LoginRequest() {

	}

	public LoginRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}