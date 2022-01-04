package fr.telecom.Poc.Security.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.telecom.Poc.Models.Personne;
import fr.telecom.Poc.Services.ServicesImpl.PersonneServiceImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	PersonneServiceImpl personneService;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Personne> user = personneService.findPersonneByUsername(username);
		
		if(user.isEmpty()) {
			new UsernameNotFoundException("No user with username : " + username);
		}
		
		return UserDetailsImpl.build(user.get());
	}

}
