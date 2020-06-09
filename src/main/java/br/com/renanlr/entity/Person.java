package br.com.renanlr.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.renanlr.enums.PersonType;

@Entity
public class Person {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	@Size(max = 100, message="{person.name.size}")
	@Pattern(regexp="^([^0-9]*)$", message="{person.name.pattern}")
	@NotBlank(message="{person.name.vazio}")
	private String name;
	
	@Column
	@Pattern(regexp="^([0-9]*)$", message="{person.document.pattern}")
	@NotBlank(message="{person.document.vazio}")
	private String document;
	
	@Column(name="birth_date")
	@NotBlank(message="{person.birth.date.vazio}")
	@PastOrPresent(message="{person.birth.date.past}")
	private Date birthDate;
	
	@Column(name="mother_name")
	@Size(max = 100, message="{person.name.mae.size}")
	@Pattern(regexp="^([^0-9]*)$", message="{person.name.mae.pattern}")
	private String motherName;
	
	@Column(name="father_name")
	@Size(max = 100, message="{person.name.pai.size}")
	@Pattern(regexp="^([^0-9]*)$", message="{person.name.pai.pattern}")
	private String fatherName;
	
	@Column(name = "register_date")
	private Date registerDate;
	
	@Column(name="operator_login")
	private String operatorLogin;

	@Column(name="person_type")
	private PersonType personType;
	
	
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


	public String getDocument() {
		return document;
	}


	public void setDocument(String document) {
		this.document = document;
	}


	public Date getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	public String getMotherName() {
		return motherName;
	}


	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}


	public String getFatherName() {
		return fatherName;
	}


	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}


	public Date getRegisterDate() {
		return registerDate;
	}


	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}


	public String getOperatorLogin() {
		return operatorLogin;
	}


	public void setOperatorLogin(String operatorLogin) {
		this.operatorLogin = operatorLogin;
	}


	public PersonType getPersonType() {
		return personType;
	}


	public void setPersonType(PersonType personType) {
		this.personType = personType;
	}
	
	
	
	
}
