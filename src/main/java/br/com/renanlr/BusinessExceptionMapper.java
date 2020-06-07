package br.com.renanlr;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.renanlr.dto.MensagemErroDto;
import br.com.renanlr.exception.BusinessException;

@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException>{

	@Override
	public Response toResponse(BusinessException exception) {
		return Response
	            .status(Response.Status.BAD_REQUEST)
	            .entity(MensagemErroDto.build(exception.getMessages()))
	            .build();
	}


}
