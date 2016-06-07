package br.ufc.npi.bootest.service;

import org.springframework.stereotype.Service;

import br.ufc.npi.bootest.model.Contact;
import br.ufc.npi.bootest.repository.ContactRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;

	public void addOrUpdate(Contact contact) {
		contactRepository.save(contact);
	}

	public List<Contact> list() {
		return (List<Contact>) contactRepository.findAll();
	}

	public void delete(Integer id) {
		if (contactRepository.findOne(id) != null)
			contactRepository.delete(id);
	}

	public Contact get(Integer id) {
		return contactRepository.findOne(id);
	}

	public List<Contact> getByName(String name) {
		return contactRepository.findContactsByName(name);
	}

	public Contact getByPhoneNumber(String phoneNumber) {
		return contactRepository.findContactByPhoneNumber(phoneNumber);
	}
}