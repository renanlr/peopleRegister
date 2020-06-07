package br.com.renanlr.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.renanlr.business.AgendamentoEmailBusiness;
import br.com.renanlr.entity.AgendamentoEmail;
import br.com.renanlr.exception.BusinessException;

@Path("/agendamentoemail")
public class AgendamentoEmailResource {
	
	@Inject
	AgendamentoEmailBusiness agendamentoEmailBusiness;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarAgendamentosEmail() {
		List<AgendamentoEmail> emails = agendamentoEmailBusiness.listarAgendamentosEmail();
		return Response.ok(emails).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvarAgendamentoEmail(AgendamentoEmail agendamentoEmail) throws BusinessException {
		agendamentoEmailBusiness.salvarAgendamentoEmail(agendamentoEmail);
		return Response.status(201).build();
	}
	
}
