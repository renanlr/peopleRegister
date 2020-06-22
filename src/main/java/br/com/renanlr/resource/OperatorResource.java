package br.com.renanlr.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.renanlr.business.OperatorBusiness;
import br.com.renanlr.entity.Operator;
import br.com.renanlr.exception.BusinessException;

@Path("/operator")
public class OperatorResource {
	
	@Inject
	private OperatorBusiness operatorBusiness;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listOperators() {
		List<Operator> operator = operatorBusiness.listOperators();
		return Response.ok(operator).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findOperator(@PathParam("id") Long id) throws BusinessException {
		Operator operator = operatorBusiness.findOperator(id);
		return Response.ok(operator).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveOperator(Operator operator) throws BusinessException {
		Operator op = operatorBusiness.saveOperator(operator);
		return Response.status(Response.Status.CREATED).entity(op).build();
	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateOperator(@PathParam("id") Long id, Operator operator) throws BusinessException {
		Operator op = operatorBusiness.updateOperator(id, operator);
		return Response.ok(op).build();
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteOperator(@PathParam("id") Long id) throws BusinessException {
		operatorBusiness.deleteOperator(id);
		return Response.ok().build();
	}

}
