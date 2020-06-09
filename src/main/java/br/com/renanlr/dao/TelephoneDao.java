package br.com.renanlr.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.renanlr.entity.Person;
import br.com.renanlr.entity.Telephone;

@Stateless
public class TelephoneDao {

	@PersistenceContext
	private EntityManager entityManager;

	public void saveTelephone(Telephone telephone) {
		entityManager.merge(telephone);
	}
	
	public Telephone findById(Long id) {
		return entityManager.find(Telephone.class, id);
	}

	public void deleteTelephone(Long id) {
		Telephone telephone = this.findById(id);
		entityManager.remove(telephone);
	}

}