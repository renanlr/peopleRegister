package br.com.renanlr.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.renanlr.enums.PersonType;

@Entity
public class Person {

	@Id
	@GeneratedValue
	private Long id;

	@Column
	@Size(max = 100, message = "{person.name.size}")
	@Pattern(regexp = "^([^0-9]*)$", message = "{person.name.pattern}")
	@NotBlank(message = "{person.name.vazio}")
	private String name;

	@Column
	@Pattern(regexp = "^([0-9]*)$", message = "{person.document.pattern}")
	@NotBlank(message = "{person.document.vazio}")
	private String document;

	@Column(name = "birth_date")
	@NotNull(message = "{person.birth.date.vazio}")
	@PastOrPresent(message = "{person.birth.date.past}")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date birthDate;

	@Column(name = "mother_name")
	@Size(max = 100, message = "{person.name.mae.size}")
	@Pattern(regexp = "^([^0-9]*)$", message = "{person.name.mae.pattern}")
	private String motherName;

	@Column(name = "father_name")
	@Size(max = 100, message = "{person.name.pai.size}")
	@Pattern(regexp = "^([^0-9]*)$", message = "{person.name.pai.pattern}")
	private String fatherName;

	@Column(name = "register_date")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date registerDate;

	@Column(name = "operator_login")
	private String operatorLogin;

	@Column(name = "person_type")
	@NotNull(message = "{}")
	private PersonType personType;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "person", orphanRemoval=true, fetch = FetchType.EAGER)
	private List<Telephone> telephones = new ArrayList<>();

	@PrePersist
	void registerDate() {
		this.registerDate = new Date();
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

	public List<Telephone> getTelephones() {
		return telephones;
	}

	public void setTelephones(List<Telephone> telephones) {
		this.telephones.clear();
		this.telephones.addAll(telephones);
	}

}
