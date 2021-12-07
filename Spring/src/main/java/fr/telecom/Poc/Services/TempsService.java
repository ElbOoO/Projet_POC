package fr.telecom.Poc.Services;

import java.util.List;
import java.util.Optional;

import fr.telecom.Poc.Models.Temps;

public interface TempsService {
	public List<Temps> findAllTemps();
	
	public Optional<Temps> findTask(Integer id);
}
