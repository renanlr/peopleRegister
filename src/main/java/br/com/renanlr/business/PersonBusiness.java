package br.com.renanlr.business;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import br.com.renanlr.dao.PersonDao;
import br.com.renanlr.entity.Person;
import br.com.renanlr.enums.Profile;
import br.com.renanlr.exception.BusinessException;
import br.com.renanlr.interceptor.Logger;

@Stateless
@Logger
public class PersonBusiness {

	@Inject
	private PersonDao personDao;

	public List<Person> listPersons() {
		return personDao.listPersons();
	}

	public void savePerson(@Valid Person person) throws BusinessException {
		if (!personDao.getPersonByDocument(person.getDocument()).isEmpty()) {
			throw new BusinessException("Login indisponível");
		}
		personDao.savePerson(person);
	}

	public Person findPerson(Long id) throws BusinessException {
		Person person = personDao.findById(id);
		if (person == null) {
			throw new BusinessException("Não existe pessoa com o codigo informado.");
		}
		return person;
	}

	public Person updatePerson(Long id, Person alteredPerson) throws BusinessException {
		Person person = personDao.findById(id);
		if (person == null) {
			throw new BusinessException("Não existe pessoa com o codigo informado.");
		}
		if (alteredPerson.getDocument() != null) {
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
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Person>> violations = validator.validate(person);
		if(!violations.isEmpty()) {
			throw new ConstraintViolationException("",violations);
		}
		
		return personDao.updatePerson(person);
	}

	public void deletePerson(Long id) throws BusinessException {
		Person Person = personDao.findById(id);
		if (Person == null) {
			throw new BusinessException("Não existe pessoa com o codigo informado.");
		}
		personDao.deletePerson(id);
	}

}
