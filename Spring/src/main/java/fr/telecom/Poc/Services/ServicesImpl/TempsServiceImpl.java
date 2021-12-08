package fr.telecom.Poc.Services.ServicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.telecom.Poc.Models.Personne;
import fr.telecom.Poc.Models.Temps;
import fr.telecom.Poc.Repositories.TempsRepository;
import fr.telecom.Poc.Services.TempsService;

@Service
public class TempsServiceImpl implements TempsService {

	@Autowired
	TempsRepository tempsRepo;
	
	@Override
	public List<Temps> findAllTemps() {
		return tempsRepo.findAll();
	}

	@Override
	public Optional<Temps> findTemps(Integer id) {
		return tempsRepo.findById(id);
	}

	@Override
	public List<Temps> findByUtilisateur(Personne utilisateur) {
		return this.tempsRepo.findByUtilisateur(utilisateur);
	}

}
