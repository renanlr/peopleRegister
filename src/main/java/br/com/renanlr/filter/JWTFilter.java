package br.com.renanlr.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.renanlr.util.JWTUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.SignatureException;

public class JWTFilter implements Filter{
	
	@Override
    public void init(FilterConfig filterConfig) throws ServletException {}
	
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
	        return;
	    }

	    try {
	        Jws<Claims> parser = JWTUtil.decode(token);
	        filterChain.doFilter(servletRequest, servletResponse);
	    } catch (SignatureException e) {
	        res.setStatus(401);
	    }

	}
	
	@Override
    public void destroy() {}
	
}