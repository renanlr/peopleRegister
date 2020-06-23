package br.com.renanlr.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;

import br.com.renanlr.dao.PersonDao;
import br.com.renanlr.entity.Person;
import br.com.renanlr.entity.Telephone;
import br.com.renanlr.exception.BusinessException;
import br.com.renanlr.interceptor.Logger;
import br.com.renanlr.util.CpfCnpjUtil;
import br.com.renanlr.util.JWTUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@Stateless
@Logger
public class PersonBusiness {

	@Inject
	private PersonDao personDao;

	public List<Person> listPersons() {
		return personDao.listPersons();
	}

	public Person savePerson(@Valid Person person, String token) throws BusinessException {
		if (!CpfCnpjUtil.isValid(person.getDocument())) {			
			throw new BusinessException("Número do Documento Inválido.");
		}
		
		Jws<Claims> jws = JWTUtil.decode(token);
		String login = jws.getBody().getSubject();
		person.setOperatorLogin(login);
		
		return personDao.savePerson(person);
	}

	public Person findPerson(Long id) throws BusinessException {
		Person person = personDao.findById(id);
		if (person == null) {
			throw new BusinessException("Não existe pessoa com o codigo informado.");
		}
		return person;
	}

	public Person updatePerson(Long id, @Valid Person alteredPerson, String token) throws BusinessException {
		Person person = personDao.findById(id);
		if (person == null) {
			throw new BusinessException("Não existe pessoa com o codigo informado.");
		}
		if (!person.getDocument().equals(alteredPerson.getDocument())) {
			throw new BusinessException("O documento não pode ser alterado.");
		}		
		if (alteredPerson.getName() != null) {
			person.setName(alteredPerson.getName());
		}
		if (alteredPerson.getMotherName() != null) {
			person.setMotherName(alteredPerson.getMotherName());
		}
		if (alteredPerson.getFatherName() != null) {
			person.setFatherName(alteredPerson.getFatherName());
		}
		if (alteredPerson.getBirthDate() != null) {
			person.setBirthDate(alteredPerson.getBirthDate());
		}
		if (!alteredPerson.getTelephones().isEmpty()) {
			for(Telephone t : alteredPerson.getTelephones()) {
				t.setPerson(person);
				Jws<Claims> jws = JWTUtil.decode(token);
				String login = jws.getBody().getSubject();
				t.setOperatorLogin(login);
			}
			person.setTelephones(alteredPerson.getTelephones());
		}
		
		return personDao.updatePerson(alteredPerson);
	}

	public void deletePerson(Long id) throws BusinessException {
		Person person = personDao.findById(id);
		if (person == null) {
			throw new BusinessException("Não existe pessoa com o codigo informado.");
		}
		personDao.deletePerson(id);
	}

}
