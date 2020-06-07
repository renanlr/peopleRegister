package br.com.renanlr.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.renanlr.entity.AgendamentoEmail;

@Stateless
public class AgendamentoEmailDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<AgendamentoEmail> listarAgendamentosEmail(){
		return entityManager
				.createQuery("select a from AgendamentoEmail a", AgendamentoEmail.class)
				.getResultList();
	}
	
	public void salvarAgendamentoEmail(AgendamentoEmail agendamentoEmail) {
		entityManager.persist(agendamentoEmail);
	}
	
	public List<AgendamentoEmail> listarAgendamentoEmailPorEmail(String email){
		Query query = entityManager.createQuery(
				"select a from AgendamentoEmail a where a.email =:email and a.enviado = false", 
				AgendamentoEmail.class);
		query.setParameter("email", email);
		return query.getResultList();
	}
	
}
