package fr.telecom.Poc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import fr.telecom.Poc.Models.Personne;
import fr.telecom.Poc.Payloads.Requests.LoginRequest;
import fr.telecom.Poc.Payloads.Requests.NouvellePersonneRequest;
import fr.telecom.Poc.Services.PersonneService;
import fr.telecom.Poc.Utils.ListeRoles;

@SpringBootTest
@WebAppConfiguration
class PersonneControllerTest {

	private MockMvc mvc;

	@Autowired
	private WebApplicationContext webAppContext;

	@Autowired
	private PersonneService personneService;

	@Autowired
	private ObjectMapper objMapper;

	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}

	@Test
	void nouvellePersonneTest() {
		this.setUp();
		this.objMapper.registerModule(new JavaTimeModule());

		Optional<Personne> greg = this.personneService.findPersonneByUsername("gregoire.biron");
		if (greg.isEmpty()) {
			fail("Impossible de trouver Gregoire Biron dans la base de donnees.");
		}
		NouvellePersonneRequest nouvellePersonne = new NouvellePersonneRequest("Devevey", "Clément", "omegaLUL",
				ListeRoles.ROLE_User.toString(), greg.get().getId());

		LoginRequest loginRequest = new LoginRequest("ruben.feliciano", "password");

		try {
			String authBody = this.objMapper.writeValueAsString(loginRequest);

			// On s'authentifie
			mvc.perform(MockMvcRequestBuilders.post("/auth/login").contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(authBody)).andReturn();

			String body = this.objMapper.writeValueAsString(nouvellePersonne);

			// Requete d'ajout d'une personne
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/personnes")
					.contentType(MediaType.APPLICATION_JSON_VALUE).content(body)).andReturn();

			assertEquals(200, mvcResult.getResponse().getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	@Test
	void getAllPersonnesTest() {
		this.setUp();
		this.objMapper.registerModule(new JavaTimeModule());

		LoginRequest loginRequest = new LoginRequest("ruben.feliciano", "password");

		try {
			String authBody = this.objMapper.writeValueAsString(loginRequest);

			// On s'authentifie
			mvc.perform(MockMvcRequestBuilders.post("/auth/login").contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(authBody)).andReturn();

			// Requete de recuperation de tous les utilisateurs
			MvcResult mvcResult = mvc
					.perform(MockMvcRequestBuilders.get("/personnes").contentType(MediaType.APPLICATION_JSON_VALUE))
					.andReturn();

			assertEquals(200, mvcResult.getResponse().getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

}
