package fr.telecom.Poc;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import fr.telecom.Poc.Models.Personne;
import fr.telecom.Poc.Models.Projet;
import fr.telecom.Poc.Models.Temps;
import fr.telecom.Poc.Repositories.PersonneRepository;
import fr.telecom.Poc.Repositories.ProjetRepository;
import fr.telecom.Poc.Repositories.TempsRepository;

@Component
public class TestDBRunner implements CommandLineRunner {
	// Remplie la database poc_db pour des tests plus tard
	
	@Autowired
	PersonneRepository personneRepo;
	
	@Autowired
	ProjetRepository projetRepo;
	
	@Autowired
	TempsRepository tempsRepo;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("--- Début du runner ---");
		
		Personne thomas = new Personne("Gagnaire", "Thomas", "1234", "user");
		Personne gregoire = new Personne("Biron", "Gregoire", "azerty", "manager");
		Personne ruben = new Personne("Feliciano", "Ruben", "password", "admin");
		
		thomas.setManager(gregoire);
		
		Projet poc = new Projet("Projet POC", gregoire);
		Projet pri = new Projet("Projet PRI", gregoire);
		Projet ntiers = new Projet("Projet Architecture N-tiers", ruben);
		
		Temps t1 = new Temps(new java.sql.Date(new Date().getTime()), 0.5, thomas, poc);
		Temps t2 = new Temps(new java.sql.Date(new Date().getTime()), 0.25, gregoire, pri);
		Temps t3 = new Temps(new java.sql.Date(new Date().getTime()), 1.0, ruben, ntiers);
		
		// --- On remplit la bdd ---
		/*
		personneRepo.save(gregoire);
		personneRepo.save(thomas);
		personneRepo.save(ruben);
		
		projetRepo.save(poc);
		projetRepo.save(pri);
		projetRepo.save(ntiers);
		
		tempsRepo.save(t1);
		tempsRepo.save(t2);
		tempsRepo.save(t3);
		*/
		
		// --- Tests ---
		Optional<Personne> greg = personneRepo.findById(170);
		System.out.println(personneRepo.findByManager(greg.get()));
		
		System.out.println("--- Fin du runner ---");
	}

}
