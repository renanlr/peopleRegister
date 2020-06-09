package br.com.renanlr.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.renanlr.business.AuthorizationBusiness;
import br.com.renanlr.dto.MensagemErroDto;
import br.com.renanlr.entity.Operator;
import br.com.renanlr.enums.Profile;
import br.com.renanlr.exception.UnauthorizedException;
import br.com.renanlr.util.JWTUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.SignatureException;

public class JWTFilter implements Filter{
	
	@Override
    public void init(FilterConfig filterConfig) throws ServletException {}
	
	@Inject
	private AuthorizationBusiness authorizationBusiness;
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
	    HttpServletRequest req = (HttpServletRequest) servletRequest;
	    HttpServletResponse res = (HttpServletResponse) servletResponse;

	    if(req.getRequestURI().startsWith("/api/login")){
	        filterChain.doFilter(servletRequest, servletResponse);
	        return;
	    }

	    String token = req.getHeader(JWTUtil.TOKEN_HEADER);

	    if(token == null || token.trim().isEmpty()){
	        res.setStatus(401);
	        String json = new ObjectMapper().writeValueAsString(MensagemErroDto.build("Nao autorizado"));
	        res.getWriter().write(json);
	        res.flushBuffer();
	        return;
	    } 
	    

	    try {
	    	Operator operator = authorizationBusiness.checkToken(token);
	    	if(!checkResourceAccess(operator.getProfile(), req)) {
	    		throw new UnauthorizedException();
	    	}
	        filterChain.doFilter(servletRequest, servletResponse);
	    } catch (SignatureException | UnauthorizedException e) {
	    	res.setStatus(401);
	    	String json = new ObjectMapper().writeValueAsString(MensagemErroDto.build("Nao autorizado"));
	        res.getWriter().write(json);
	        res.flushBuffer();
	    }

	}
	
	private boolean checkResourceAccess(Profile profile, HttpServletRequest req) {
		
		if(profile == Profile.ADMINISTRADOR) {
			return req.getRequestURI().startsWith("/api/login") ||
					req.getRequestURI().startsWith("/api/me") ||
					req.getRequestURI().startsWith("/api/logout") ||
					req.getRequestURI().startsWith("/api/operator");
		} else if(profile == Profile.GERENTE) {
			return req.getRequestURI().startsWith("/api/login") ||
					req.getRequestURI().startsWith("/api/me") ||
					req.getRequestURI().startsWith("/api/logout") ||
					req.getRequestURI().startsWith("/api/person");
		} else {
			return req.getRequestURI().startsWith("/api/login") ||
					req.getRequestURI().startsWith("/api/me") ||
					(req.getRequestURI().startsWith("/api/person") && "GET".equals(req.getMethod())) ||
					req.getRequestURI().startsWith("/api/logout");
		}
	}
	
	
	@Override
    public void destroy() {}
	
}
