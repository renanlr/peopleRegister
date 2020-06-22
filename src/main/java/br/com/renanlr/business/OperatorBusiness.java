package br.com.renanlr.business;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import br.com.renanlr.dao.OperatorDao;
import br.com.renanlr.entity.Operator;
import br.com.renanlr.enums.Profile;
import br.com.renanlr.exception.BusinessException;
import br.com.renanlr.interceptor.Logger;

@Stateless
@Logger
public class OperatorBusiness {

	@Inject
	private OperatorDao operatorDao;

	public List<Operator> listOperators() {
		return operatorDao.listOperators();
	}

	public Operator saveOperator(@Valid Operator operator) throws BusinessException {
		if (operator.getProfile().equals(Profile.ADMINISTRADOR)) {
			throw new BusinessException("Não é possível criar operador com perfil ADMINISTRADOR");
		}
		if (!operatorDao.findByLogin(operator.getLogin()).isEmpty()) {
			throw new BusinessException("O login escolhido ja está sendo utilizado por outro operador");
		}
		return operatorDao.saveOperator(operator);
	}

	public Operator findOperator(Long id) throws BusinessException {
		Operator operator = operatorDao.findById(id);
		if (operator == null) {
			throw new BusinessException("Não existe operador com o codigo informado.");
		}
		return operator;
	}

	public Operator updateOperator(Long id, Operator alteredOperator) throws BusinessException {
		Operator operator = operatorDao.findById(id);
		if (operator == null) {
			throw new BusinessException("Não existe operador com o codigo informado.");
		}
		if (alteredOperator.getLogin() != null) {
			throw new BusinessException("O login não pode ser alterado.");
		}
		if (operator.getProfile() == Profile.ADMINISTRADOR) {
			throw new BusinessException("O operador ADMINISTRADOR não pode ser editado.");
		}
		operator.setName(alteredOperator.getName());
		operator.setPassword(alteredOperator.getPassword());
		operator.setProfile(alteredOperator.getProfile());
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Operator>> violations = validator.validate(operator);
		if(!violations.isEmpty()) {
			throw new ConstraintViolationException("",violations);
		}
		
		return operatorDao.updateOperator(operator);
	}

	public void deleteOperator(Long id) throws BusinessException {
		Operator operator = operatorDao.findById(id);
		if (operator == null) {
			throw new BusinessException("Não existe operador com o codigo informado.");
		}
		if (operator.getProfile() == Profile.ADMINISTRADOR) {
			throw new BusinessException("Não é permitido remover um ADMINISTRADOR.");
		}
		operatorDao.deleteOperator(id);
	}

}
