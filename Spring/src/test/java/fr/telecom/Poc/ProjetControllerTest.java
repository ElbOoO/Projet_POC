package fr.telecom.Poc;

import static org.junit.jupiter.api.Assertions.*;

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

import fr.telecom.Poc.DTO.ProjetDTO;
import fr.telecom.Poc.Payloads.Requests.LoginRequest;

@SpringBootTest
@WebAppConfiguration
class ProjetControllerTest {

	private MockMvc mvc;

	@Autowired
	private WebApplicationContext webAppContext;

	@Autowired
	private ObjectMapper objMapper;

	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}

	@Test
	void nouveauTempsTest() {
		this.setUp();
		this.objMapper.registerModule(new JavaTimeModule());

		ProjetDTO nouveauProjet = new ProjetDTO("Projet Big Doto", "#ABCDEF", 2);

		LoginRequest loginRequest = new LoginRequest("ruben.feliciano", "password");

		try {
			String authBody = this.objMapper.writeValueAsString(loginRequest);

			// On s'authentifie
			mvc.perform(MockMvcRequestBuilders.post("/auth/login").contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(authBody)).andReturn();

			String body = this.objMapper.writeValueAsString(nouveauProjet);

			// Requete d'ajout d'une personne
			MvcResult mvcResult = mvc.perform(
					MockMvcRequestBuilders.post("/projets").contentType(MediaType.APPLICATION_JSON_VALUE).content(body))
					.andReturn();

			assertEquals(200, mvcResult.getResponse().getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	@Test
	void getAllTempsTest() {
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
					.perform(MockMvcRequestBuilders.get("/projets").contentType(MediaType.APPLICATION_JSON_VALUE))
					.andReturn();

			assertEquals(200, mvcResult.getResponse().getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

}
