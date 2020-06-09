package br.com.renanlr.business;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;

import br.com.renanlr.dao.PersonDao;
import br.com.renanlr.dao.TelephoneDao;
import br.com.renanlr.entity.Person;
import br.com.renanlr.entity.Telephone;
import br.com.renanlr.exception.BusinessException;
import br.com.renanlr.interceptor.Logger;
import br.com.renanlr.util.JWTUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@Stateless
@Logger
public class TelephoneBusiness {
	
	@Inject
	private TelephoneDao telephoneDao;
	
	@Inject 
	private	PersonDao personDao;
	
	public void saveTelephone(@Valid Telephone telephone, String token, Long personId) throws BusinessException {		
		Jws<Claims> jws = JWTUtil.decode(token);
		String login = jws.getBody().getSubject();
		telephone.setOperatorLogin(login);
		Person person = personDao.findById(personId);
		telephone.setPerson(person);
		telephoneDao.saveTelephone(telephone);
	}
	
	public void deleteTelephone(Long id) throws BusinessException {
		telephoneDao.deleteTelephone(id);
	}

}
