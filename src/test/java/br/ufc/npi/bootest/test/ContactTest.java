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
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import br.ufc.npi.bootest.model.Contact;
import br.ufc.npi.bootest.service.ContactService;

public class ContactTest extends GenericTest {

	@Autowired
	private WebApplicationContext context;
	
	@Mock
	private ContactService contactService;
	
	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
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
		Contact contact = new Contact();
		contact.setName("Matheus");
		contact.setPhoneNumber("988776655");
		
		Mockito.when(contactService.getByPhoneNumber("988776655")).thenReturn(contact);
		
		mockMvc
			.perform(post("/c/add")
				.param("nome", contact.getName())
				.param("telefone", contact.getPhoneNumber())
			)
			.andExpect(redirectedUrl("/c"));

		Mockito.verify(contactService, Mockito.times(1)).getByPhoneNumber(contact.getPhoneNumber());
	}

	@Test
	public void delete() throws Exception {
		Contact contact = new Contact();
		contact.setName("Matheus");
		contact.setPhoneNumber("988776655");

		Mockito.when(contactService.get(13)).thenReturn(contact);

		mockMvc.perform(get("/c/rm/13"))
		.andExpect(status().isFound())
		//.andExpect(flash().attribute("alertSuccess", "Take out the garbage successfully completed"))
		.andExpect(redirectedUrl("/c"));

		Mockito.verify(contactService, Mockito.times(0)).get(13);
	}
}