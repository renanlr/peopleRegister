package br.com.renanlr.dto;

import br.com.renanlr.entity.Operator;
import br.com.renanlr.enums.Profile;

public class UserLogged {
	
	private String login;
	private String token;
	private Profile profile;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	public static UserLogged build(Operator operator) {
		UserLogged userLogged = new UserLogged();
		userLogged.setLogin(operator.getLogin());
		userLogged.setToken(operator.getToken());
		userLogged.setProfile(operator.getProfile());
		
		return userLogged;
	}
	
	

}
