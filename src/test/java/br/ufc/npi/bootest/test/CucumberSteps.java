package br.ufc.npi.bootest.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.ufc.npi.bootest.controller.ContactController;
import br.ufc.npi.bootest.model.Contact;
import br.ufc.npi.bootest.service.ContactService;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;


public class CucumberSteps {

	@InjectMocks
    private ContactController contactController;
	
	@Mock
	private ContactService contactService;
	
	private MockMvc mockMvc;
	private Contact contact;
	private WebDriver drive;
	@cucumber.api.java.Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(contactController).build();
	}

	
	@Dado("^eu estou na tela de adicionar novo contato$")
    public void estou_em_adicionar_contato() throws Throwable {
		List<Contact> contacts = new ArrayList<Contact>();

		Mockito.when(contactService.list()).thenReturn(contacts);

		mockMvc
			.perform(get("/c"))
			.andExpect(model().attribute("contactsList", contacts))
			.andExpect(view().name("contact/list"));
    }

	@Quando("^eu adiciono um novo contato com nome (.*) e telefone (.*)$")
	public void adiciono_novo_contato(String nome, String telefone)throws Throwable{
		Contact contact = new Contact();
		contact.setName(nome);
		contact.setPhoneNumber(telefone);
		
		Mockito.when(contactService.getByPhoneNumber(telefone)).thenReturn(contact);
		
		mockMvc
			.perform(post("/c/add")
				.param("name", contact.getName())
				.param("phoneNumber", contact.getPhoneNumber())
			)
			.andExpect(redirectedUrl("/c"));
	}
	
	@Entao("^o contato é adicionado a lista$")
	public void contato_existe_na_lista() throws Throwable{
		
	
	}
	
	
	@Dado("^eu estou na tela inicial$")
    public void estou_na_lista_de_contatos() throws Throwable {
	

    }
    
    @Quando("^removo um contato com nome (.*) e telefone (.*)$")
    public void eu_adiciono_novo_contato(String nome, String telefone) throws Throwable {
    	contact = new Contact();
		contact.setName(nome);
		contact.setPhoneNumber(telefone);
    }
    
    

    
	@Entao("^o contato é removido da lista$")
    public void contato_removido_da_lista() throws Throwable {
		
		Mockito.when(contactService.get(13)).thenReturn(contact);

		mockMvc
			.perform(get("/c/rm/13"))
			.andExpect(status().isFound())
			.andExpect(redirectedUrl("/c"));

		Mockito.verify(contactService, Mockito.times(0)).get(13);
    }
	
	
	@Dado("^Abri o firefox e estou na tela de adicionar contato$")
	public void abri_firefox_na_tela_adicionar_contato() throws Throwable {
			drive = new FirefoxDriver();
			drive.manage().window().maximize();
			drive.get("http://localhost:8080/bootest/c/add");
		}

	@Quando("^eu adicionar nome (.*) e telefone (.*)$")
	public void adiciono_um_contato_valido(String nome, String telefone) throws Throwable {
		drive.findElement(By.id("name")).sendKeys(nome);
		drive.findElement(By.id("phoneNumber")).sendKeys(telefone);
	}

	@Entao("^o usuario devera ser redirecionado para a tela principal$")
	public void tela_principal_mostrada_para_o_usuario() throws Throwable {
		drive.findElement(By.id("save")).click();
		Assert.assertEquals("http://localhost:8080/bootest/c", drive.getCurrentUrl());
	}
}