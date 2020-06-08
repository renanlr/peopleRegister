package br.com.renanlr.resource;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.renanlr.business.AuthorizationBusiness;
import br.com.renanlr.dao.OperatorDao;
import br.com.renanlr.dto.Credentials;
import br.com.renanlr.dto.UserLogged;
import br.com.renanlr.entity.Operator;
import br.com.renanlr.exception.BusinessException;
import br.com.renanlr.exception.UnauthorizedException;
import br.com.renanlr.util.JWTUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@Path("/")
public class AuthorizationResource {

	@Inject
	private AuthorizationBusiness authorizationBusiness;
	
	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(Credentials credentials) throws UnauthorizedException {
		Operator operator = authorizationBusiness.login(credentials.getUsername(), credentials.getPassword());
		return Response.ok()
	    		.entity(UserLogged.build(operator))
	    		.build();
	}
	
	@GET
	@Path("me")
	@Produces(MediaType.APPLICATION_JSON)
	public Response me(@Context HttpServletRequest httpRequest) throws UnauthorizedException {
	    String token = httpRequest.getHeader(JWTUtil.TOKEN_HEADER);
	    Operator operator = authorizationBusiness.checkToken(token);
	    return Response.ok()
	    		.entity(UserLogged.build(operator))
	    		.build();
	}
	
	@GET
	@Path("logout")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@Context HttpServletRequest httpRequest) throws UnauthorizedException {
		String token = httpRequest.getHeader(JWTUtil.TOKEN_HEADER);
		authorizationBusiness.logout(token);
        return Response.ok().build();
	}
}
