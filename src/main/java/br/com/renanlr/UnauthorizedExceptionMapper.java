package br.com.renanlr;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.renanlr.dto.MensagemErroDto;
import br.com.renanlr.exception.BusinessException;
import br.com.renanlr.exception.UnauthorizedException;

@Provider
public class UnauthorizedExceptionMapper implements ExceptionMapper<UnauthorizedException>{
	
	@Override
	public Response toResponse(UnauthorizedException exception) {
		return Response
	            .status(Response.Status.UNAUTHORIZED)
	            .entity(MensagemErroDto.build(exception.getMessages()))
	            .build();
	}

}
