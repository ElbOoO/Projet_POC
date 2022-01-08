package fr.telecom.Poc.Utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import fr.telecom.Poc.Models.Personne;
import fr.telecom.Poc.Models.Projet;
import fr.telecom.Poc.Models.Temps;
import fr.telecom.Poc.Models.VerrouillageTemps;
import fr.telecom.Poc.Repositories.PersonneRepository;
import fr.telecom.Poc.Repositories.ProjetRepository;
import fr.telecom.Poc.Repositories.TempsRepository;
import fr.telecom.Poc.Repositories.VerrouillageTempsRepository;
import fr.telecom.Poc.Services.VerrouillageTempsService;
import fr.telecom.Poc.Services.ServicesImpl.VerrouillageTempsServiceImpl;

@Component
public class DBRunner implements CommandLineRunner {
	// Remplie la database poc_db pour des tests plus tard

	@Autowired
	PersonneRepository personneRepo;

	@Autowired
	ProjetRepository projetRepo;

	@Autowired
	TempsRepository tempsRepo;

	@Autowired
	VerrouillageTempsRepository verrouRepo;

	@Autowired
	VerrouillageTempsServiceImpl verrouService;

	@Autowired
	PasswordEncoder encoder;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("--- Début du runner ---");

		Personne thomas = new Personne("Gagnaire", "Thomas", encoder.encode("1234"), ListeRoles.ROLE_User.name());
		Personne gregoire = new Personne("Biron", "Gregoire", encoder.encode("azerty"), ListeRoles.ROLE_Manager.name());
		Personne ruben = new Personne("Feliciano", "Ruben", encoder.encode("password"), ListeRoles.ROLE_Admin.name());

		thomas.setManager(gregoire);

		Projet poc = new Projet("Projet POC", "#FF5733", gregoire);
		Projet pri = new Projet("Projet PRI", "#37B850", gregoire);
		Projet ntiers = new Projet("Projet Architecture N-tiers", "#31AEF6", ruben);

		Temps t1 = new Temps(new java.sql.Date(new Date().getTime()), 0.5, thomas, poc);
		Temps t2 = new Temps(new java.sql.Date(new Date().getTime()), 0.25, gregoire, pri);
		Temps t3 = new Temps(new java.sql.Date(new Date().getTime()), 1.0, ruben, ntiers);

		VerrouillageTemps verrou = new VerrouillageTemps(1, 2022, ruben);

		// --- On remplit la bdd ---
		try {
			personneRepo.save(gregoire);
			personneRepo.save(thomas);
			personneRepo.save(ruben);

			projetRepo.save(poc);
			projetRepo.save(pri);
			projetRepo.save(ntiers);

			tempsRepo.save(t1);
			tempsRepo.save(t2);
			tempsRepo.save(t3);

			verrouRepo.save(verrou);
		} catch (DataIntegrityViolationException e) {
			System.out.println("Cet utilisateur existe déjà");
		}

		System.out.println("--- Fin du runner ---");
	}

}
