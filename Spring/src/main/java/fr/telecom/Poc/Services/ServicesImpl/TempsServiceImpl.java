package fr.telecom.Poc.Services.ServicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import fr.telecom.Poc.Models.Temps;
import fr.telecom.Poc.Repositories.TempsRepository;
import fr.telecom.Poc.Services.TempsService;

public class TempsServiceImpl implements TempsService {

	@Autowired
	TempsRepository tempsRepo;
	
	@Override
	public List<Temps> findAllTemps() {
		return tempsRepo.findAll();
	}

	@Override
	public Optional<Temps> findTask(Integer id) {
		return tempsRepo.findById(id);
	}

}
