package fr.telecom.Poc.DTO;

import fr.telecom.Poc.Models.Projet;

/**
 * Classe de Data Transfert Object utilisee par l'API pour renvoyer des Projets
 */
public class ProjetDTO {

	private Integer id;
	private String nom;
	private String couleur;
	private Integer manager;

	public ProjetDTO() {

	}

	public ProjetDTO(String nom, String couleur, Integer managerId) {
		this.nom = nom;
		this.couleur = couleur;
		this.manager = managerId;
	}

	public ProjetDTO(Projet p) {
		this.id = p.getId();
		this.nom = p.getNom();
		this.couleur = p.getCouleur();
		this.manager = p.getManager().getId();
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

	public Integer getManager() {
		return manager;
	}

	public void setManager(Integer manager) {
		this.manager = manager;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

}
