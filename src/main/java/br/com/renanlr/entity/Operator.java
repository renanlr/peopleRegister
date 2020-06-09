package br.com.renanlr.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.renanlr.enums.Profile;

@Entity
public class Operator {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	@Size(max = 100, message="{operator.name.size}")
	@Pattern(regexp="^([^0-9]*)$", message="{operator.name.pattern}")
	@NotBlank(message="{operator.name.vazio}")
	private String name;
	
	@Column
	@Size(max = 15, message="{operator.login.size}")
	@Pattern(regexp="^[a-zA-Z_-]*$", message="{operator.login.pattern}")
	@NotBlank(message="{operator.login.vazio}")
	private String login;
	
	@Column
	@Size(min = 6, max = 15, message="{operator.password.size}")
	@Pattern(regexp="^([^\\s]*)$", message="{operator.password.pattern}")
	@NotBlank(message="{operator.password.vazio}")
	private String password;
	
	@Column
	private Profile profile;
	
	@Column(name = "register_date")
	private Date registerDate;
	
	@Column
	private String token;
	
	@PrePersist
	void registerDate() {
		java.util.Date dt = new java.util.Date();
		this.registerDate = new Date(dt.getTime());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
}
