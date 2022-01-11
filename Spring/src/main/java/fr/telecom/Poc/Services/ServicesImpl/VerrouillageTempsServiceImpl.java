package fr.telecom.Poc.Services.ServicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.telecom.Poc.Models.Personne;
import fr.telecom.Poc.Models.VerrouillageTemps;
import fr.telecom.Poc.Repositories.VerrouillageTempsRepository;
import fr.telecom.Poc.Services.VerrouillageTempsService;

@Service
public class VerrouillageTempsServiceImpl implements VerrouillageTempsService {

	@Autowired
	VerrouillageTempsRepository verrouRepo;

	@Override
	public List<VerrouillageTemps> findAllLocks() {
		return this.verrouRepo.findAll();
	}

	@Override
	public Boolean isLocked(Integer mois, Integer annee, Personne utilisateur) {
		Optional<VerrouillageTemps> verrou = this.verrouRepo.findByMoisAndAnneeAndUtilisateur(mois, annee, utilisateur);

		if (verrou.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

}
