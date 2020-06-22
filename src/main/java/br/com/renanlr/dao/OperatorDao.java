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
	
	public Operator saveOperator(Operator operator) {
		entityManager.persist(operator);
		return operator;
	}
	
	public Operator updateOperator(Operator operator) {
		return entityManager.merge(operator);
	}
	
	public List<Operator> findByLogin(String login){
		Query query = entityManager.createQuery(
				"select o from Operator o where o.login =:login", 
				Operator.class);
		query.setParameter("login", login);
		return query.getResultList();
	}
	
	public List<Operator> authenticateOperator(String login, String password){
		Query query = entityManager.createQuery(
				"select o from Operator o where o.login =:login and o.password =:password", 
				Operator.class);
		query.setParameter("login", login);
		query.setParameter("password", password);
		return query.getResultList();
	}

	public void deleteOperator(Long id) {
		Operator operator = this.findById(id);
		entityManager.remove(operator);
	}

	public List<Operator> checkToken(String login, String token) {
		Query query = entityManager.createQuery(
				"select o from Operator o where o.login =:login and o.token =:token", 
				Operator.class);
		query.setParameter("login", login);
		query.setParameter("token", token);
		return query.getResultList();
	}

	public void deleteToken(Operator operator) {
		operator.setToken(null);
		entityManager.merge(operator);
	}

}
