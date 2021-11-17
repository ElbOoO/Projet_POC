package fr.telecom.Poc.Models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Relations {
	@ManyToOne
	private Personne p1;
	@ManyToOne
	private Personne p2;
}
