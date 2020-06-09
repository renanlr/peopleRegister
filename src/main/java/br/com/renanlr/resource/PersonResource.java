package br.com.renanlr.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.renanlr.business.PersonBusiness;
import br.com.renanlr.entity.Person;
import br.com.renanlr.exception.BusinessException;
import br.com.renanlr.util.JWTUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@Path("/person")
public class PersonResource {
	
	@Inject
	private PersonBusiness personBusiness;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listPersons() {
		List<Person> persons = personBusiness.listPersons();
		return Response.ok(persons).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findPerson(@PathParam("id") Long id) throws BusinessException {
		Person person = personBusiness.findPerson(id);
		return Response.ok(person).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response savePerson(@HeaderParam(JWTUtil.TOKEN_HEADER) String token,Person person) throws BusinessException {
		personBusiness.savePerson(person, token);
		return Response.status(Response.Status.CREATED).build();
	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePerson(@PathParam("id") Long id, Person person) throws BusinessException {
		Person per = personBusiness.updatePerson(id, person);
		return Response.ok(per).build();
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePerson(@PathParam("id") Long id) throws BusinessException {
		personBusiness.deletePerson(id);
		return Response.ok().build();
	}

}
