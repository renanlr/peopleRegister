package br.com.renanlr.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;

import br.com.renanlr.dao.AgendamentoEmailDao;
import br.com.renanlr.entity.AgendamentoEmail;
import br.com.renanlr.exception.BusinessException;
import br.com.renanlr.interceptor.Logger;

@Stateless
@Logger
public class AgendamentoEmailBusiness {
	
	@Inject
	private AgendamentoEmailDao agendamentoEmailDao;
	
	public List<AgendamentoEmail> listarAgendamentosEmail(){
		
		return agendamentoEmailDao.listarAgendamentosEmail();
		
	}
	
	public void salvarAgendamentoEmail(@Valid AgendamentoEmail agendamentoEmail) throws BusinessException {
		
		if(!agendamentoEmailDao.listarAgendamentoEmailPorEmail(agendamentoEmail.getEmail()).isEmpty()) {
			throw new BusinessException("Email ja está agendado.");
		}
		
		agendamentoEmail.setEnviado(false);
		agendamentoEmailDao.salvarAgendamentoEmail(agendamentoEmail);
	}

}
