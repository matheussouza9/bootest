package br.ufc.npi.bootest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufc.npi.bootest.model.Contact;
import br.ufc.npi.bootest.service.ContactService;

@Controller
@RequestMapping(value = {"/", "/c"})
public class ContactController {

	@Autowired
	private ContactService contactService;

	private static final String TEMPLATE_ADD_OR_EDIT = "contact/add_or_edit";
	private static final String TEMPLATE_LIST = "contact/list";

	@RequestMapping(value = "", method = RequestMethod.GET)
    public String getContacts(Model model) {
		List<Contact> contactsList = contactService.list();
		model.addAttribute("contactsList", contactsList);
        return TEMPLATE_LIST;
    }

	@RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addContact(Model model) {
		model.addAttribute("contact", new Contact());
		model.addAttribute("pageName", "Add new contact");
        return TEMPLATE_ADD_OR_EDIT;
    }

	@RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addContact(@Valid Contact contact, BindingResult result) {
		if(result.hasErrors())
			return TEMPLATE_ADD_OR_EDIT;

		contactService.addOrUpdate(contact);
        return "redirect:/c";
    }
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editContact(@PathVariable Integer id, Model model) {
		Contact contact = contactService.get(id);
		
		if(null != contact){
			model.addAttribute("contact", contact);
			model.addAttribute("pageName", "Edit a contact");
			return TEMPLATE_ADD_OR_EDIT;
		}
		
		return "redirect:/c";
    }
	
	@RequestMapping(value = "/rm/{id}", method = RequestMethod.GET)
    public String removeContact(@PathVariable Integer id, Model model) {
		contactService.delete(id);
		return "redirect:/c";
    }
}