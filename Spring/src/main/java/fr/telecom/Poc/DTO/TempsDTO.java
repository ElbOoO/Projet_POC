package fr.telecom.Poc.DTO;

import java.time.LocalDate;

import fr.telecom.Poc.Models.Temps;

/**
 * Classe de Data Transfert Object utilisee par l'API pour renvoyer des Temps
 */
public class TempsDTO {

	private Integer id;
	private LocalDate date;
	private Double poids;
	private PersonneDTO utilisateur;
	private ProjetDTO projet;
	private Boolean locked;

	public TempsDTO(Temps t) {
		this.id = t.getId();
		this.date = t.getDate();
		this.poids = t.getPoids();
		this.utilisateur = new PersonneDTO(t.getUtilisateur());
		this.projet = new ProjetDTO(t.getProjet());
		this.locked = t.getLocked();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
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

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}
}
