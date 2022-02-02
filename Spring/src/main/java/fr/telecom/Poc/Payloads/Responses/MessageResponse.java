package fr.telecom.Poc.Payloads.Responses;

/**
 * Classe utilisee pour renvoyer des messages dans les reponses de l'API
 */
public class MessageResponse {

	private String message;

	public MessageResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
