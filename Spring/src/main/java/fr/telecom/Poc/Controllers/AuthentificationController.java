package fr.telecom.Poc.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.telecom.Poc.Services.ServicesImpl.PersonneServiceImpl;
import fr.telecom.Poc.Utils.ListeRoles;
import fr.telecom.Poc.Models.Personne;
import fr.telecom.Poc.Payloads.Requests.LoginRequest;
import fr.telecom.Poc.Payloads.Requests.SignupRequest;
import fr.telecom.Poc.Payloads.Responses.JwtResponse;
import fr.telecom.Poc.Payloads.Responses.MessageResponse;
import fr.telecom.Poc.Repositories.PersonneRepository;
import fr.telecom.Poc.Security.Jwt.JwtUtils;
import fr.telecom.Poc.Security.Services.UserDetailsImpl;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/auth")
public class AuthentificationController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PersonneServiceImpl personneService;

	@Autowired
	PersonneRepository personneRepo;

	// @Autowired
	// RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles));
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
		if (personneService.findPersonneByUsername(signUpRequest.getPrenom() + "." + signUpRequest.getNom())
				.isPresent()) {
			return ResponseEntity.badRequest().body(new MessageResponse("Erreur : cet utilisateur existe déjà !"));
		}

		if (!ListeRoles.isPresent(signUpRequest.getRole())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Erreur : ce role est invalide."));

		}

		// Create new Personne
		Personne user = new Personne(signUpRequest.getNom(), signUpRequest.getPrenom(),
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getRole());

		this.personneRepo.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
