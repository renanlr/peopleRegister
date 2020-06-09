package br.com.renanlr.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.renanlr.entity.Operator;
import br.com.renanlr.entity.Person;
import br.com.renanlr.enums.PersonType;

public class PersonDto {

	private Long id;
	private String name;
	private String document;
	private String birthDate;
	private String motherName;
	private String fatherName;
	private String registerDate;
	private String operatorLogin;
	private PersonType personType;

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

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
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

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
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

	public static PersonDto build(Person person) {
		PersonDto personDto = new PersonDto();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		personDto.setId(person.getId());
		personDto.setName(person.getName());
		personDto.setDocument(person.getDocument());
		personDto.setBirthDate(sdf.format(person.getBirthDate()));
		personDto.setMotherName(person.getMotherName());
		personDto.setFatherName(person.getFatherName());
		personDto.setRegisterDate(sdf.format(person.getRegisterDate()));
		personDto.setOperatorLogin(person.getOperatorLogin());
		personDto.setPersonType(person.getPersonType());

		return personDto;
	}
	
	public static List<PersonDto> build(List<Person> persons) {
		List<PersonDto> personDtos = new ArrayList<>();
		for(Person p : persons) {
			personDtos.add(PersonDto.build(p));
		}

		return personDtos;
	}
}
