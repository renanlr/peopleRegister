package br.com.renanlr.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.renanlr.entity.Operator;

@Stateless
public class OperatorDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Operator> listOperators(){
		return entityManager
				.createQuery("select o from Operator o", Operator.class)
				.getResultList();
	}
	
	public Operator findById(Long id) {
		return entityManager.find(Operator.class, id);
	}
	
	public void saveOperator(Operator operator) {
		entityManager.persist(operator);
	}
	
	public Operator updateOperator(Operator operator) {
		return entityManager.merge(operator);
	}
	
	public List<Operator> getOperatorByLogin(String login){
		Query query = entityManager.createQuery(
				"select o from Operator o where o.login =:login", 
				Operator.class);
		query.setParameter("login", login);
		return query.getResultList();
	}

	public void deleteOperator(Long id) {
		Operator operator = this.findById(id);
		entityManager.remove(operator);
	}

}
