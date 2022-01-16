package fr.telecom.Poc.Payloads.Requests;

import java.sql.Date;

public class ExportTempsRequest {
	private Integer utilisateur;
	private Date date;

	public ExportTempsRequest() {

	}

	public ExportTempsRequest(Integer utilisateur, Date date) {
		this.utilisateur = utilisateur;
		this.date = date;
	}

	public Integer getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Integer utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
