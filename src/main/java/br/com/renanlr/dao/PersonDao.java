package br.com.renanlr.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.renanlr.entity.Person;

@Stateless
public class PersonDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Person> listPersons() {
		return entityManager.createQuery("select p from Person p", Person.class).getResultList();
	}

	public Person findById(Long id) {
		return entityManager.find(Person.class, id);
	}

	public Person savePerson(Person person) {
		entityManager.persist(person);
		return person;
	}

	public Person updatePerson(Person person) {
		return entityManager.merge(person);
	}

	public List<Person> getPersonByLogin(String login) {
		Query query = entityManager.createQuery("select p from Person p where p.operatorLogin =:login", Person.class);
		query.setParameter("login", login);
		return query.getResultList();
	}
	
	public List<Person> getPersonByDocument(String document) {
		Query query = entityManager.createQuery("select p from Person p where p.document =:document", Person.class);
		query.setParameter("document", document);
		return query.getResultList();
	}

	public void deletePerson(Long id) {
		Person person = this.findById(id);
		entityManager.remove(person);
	}

}
