package br.ufc.npi.bootest.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufc.npi.bootest.model.Contact;

@Repository
@Transactional
public interface ContactRepository extends CrudRepository<Contact, Integer> {
	public List<Contact> findContactsByName(String name);
	public Contact findContactByPhoneNumber(String phoneNumber);
}