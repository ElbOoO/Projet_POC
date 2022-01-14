package fr.telecom.Poc.DTO;

import java.sql.Date;
import fr.telecom.Poc.Models.Temps;

public class TempsDTO {

	private Integer id;
	private Date date;
	private Double poids;
	private PersonneDTO utilisateur;
	private ProjetDTO projet;

	public TempsDTO(Temps t) {
		this.id = t.getId();
		this.date = t.getDate();
		this.poids = t.getPoids();
		this.utilisateur = new PersonneDTO(t.getUtilisateur());
		this.projet = new ProjetDTO(t.getProjet());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getPoids() {
		return poids;
	}

	public void setPoids(Double poids) {
		this.poids = poids;
	}

	public PersonneDTO getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(PersonneDTO utilisateur) {
		this.utilisateur = utilisateur;
	}

	public ProjetDTO getProjet() {
		return projet;
	}

	public void setProjet(ProjetDTO projet) {
		this.projet = projet;
	}

}
