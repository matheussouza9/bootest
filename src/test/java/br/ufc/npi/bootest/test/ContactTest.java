package br.ufc.npi.bootest.test;

//import org.mockito.Matchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import br.ufc.npi.bootest.controller.ContactController;
import br.ufc.npi.bootest.model.Contact;
import br.ufc.npi.bootest.service.ContactService;

public class ContactTest extends GenericTest {
	
	@InjectMocks
    private ContactController contactController;
	
	@Mock
	private ContactService contactService;
	
	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(contactController).build();
	}

	@Test
	public void list() throws Exception {
		List<Contact> contacts = new ArrayList<Contact>();

		Mockito.when(contactService.list()).thenReturn(contacts);

		mockMvc
			.perform(get("/c"))
			.andExpect(model().attribute("contactsList", contacts))
			.andExpect(view().name("contact/list"));
	}

	@Test
	@Transactional
	public void add() throws Exception {
		mockMvc
			.perform(post("/c/add")
				.param("name", "Matheus")
				.param("phoneNumber", "988776655")
			)
			.andExpect(redirectedUrl("/c"));
	}

	@Test
	public void delete() throws Exception {
		mockMvc
			.perform(get("/c/rm/13"))
			.andExpect(status().isFound())
			.andExpect(redirectedUrl("/c"));
	}
}