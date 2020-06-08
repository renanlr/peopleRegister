package br.com.renanlr.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;

import br.com.renanlr.dao.OperatorDao;
import br.com.renanlr.entity.Operator;
import br.com.renanlr.enums.Profile;
import br.com.renanlr.exception.BusinessException;
import br.com.renanlr.exception.UnauthorizedException;
import br.com.renanlr.interceptor.Logger;
import br.com.renanlr.util.JWTUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@Stateless
@Logger
public class AuthorizationBusiness {
	
	private String ADMIN_USERNAME = "admin";
	private String ADMIN_PASSWORD = "admin123";
	
	
	@Inject
	private OperatorDao operatorDao;

	public Operator login(String login, String password) throws UnauthorizedException {
		List<Operator> operators = operatorDao.findByLogin(login);
		if(operators.isEmpty()) {
			if(this.ADMIN_USERNAME.equals(login) && this.ADMIN_PASSWORD.equals(password)) {
				return createAdminOperator(login);
			} else {				
				throw new UnauthorizedException("login ou senha inválidos.");
			}
		}
		Operator operator = operators.get(0);
		if(!operator.getPassword().equals(password)) {
			throw new UnauthorizedException("login ou senha inválidos.");
		}
		String token = JWTUtil.create(login);
		operator.setToken(token);
		operatorDao.updateOperator(operator);
		return operator;
	}
	
	public Operator checkToken(String token) throws UnauthorizedException {
		Jws<Claims> jws = JWTUtil.decode(token);
		String login = jws.getBody().getSubject();
		List<Operator> operators = operatorDao.checkToken(login, token);
		if(operators.isEmpty()) {
			throw new UnauthorizedException("token inválido.");
		}
		return operators.get(0);
	}
	
	public void logout(String token) throws UnauthorizedException {
		Jws<Claims> jws = JWTUtil.decode(token);
		String login = jws.getBody().getSubject();
		List<Operator> operators = operatorDao.checkToken(login, token);
		if(operators.isEmpty()) {
			throw new UnauthorizedException("token inválido.");
		}
		Operator operator = operators.get(0);
		operatorDao.deleteToken(operator);
	}
	
	private Operator createAdminOperator(String login) {
		String token = JWTUtil.create(login);
		Operator operator = new Operator();
		operator.setName(this.ADMIN_USERNAME);
		operator.setLogin(this.ADMIN_USERNAME);
		operator.setPassword(this.ADMIN_PASSWORD);
		operator.setProfile(Profile.ADMINISTRADOR);
		operator.setToken(token);
		operatorDao.saveOperator(operator);
		return operator;
	}

}
