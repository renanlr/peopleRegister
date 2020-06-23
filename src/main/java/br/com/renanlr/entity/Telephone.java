package br.com.renanlr.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.renanlr.enums.TelephoneType;

@Entity
@JsonIgnoreProperties("person")
public class Telephone {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	@Size(min = 3, max = 3, message="{telephone.ddd.size}")
	@NotBlank(message="{telephone.ddd.vazio}")
	private String ddd;
	
	@Column
	@Size(min = 8, max = 10, message="{telephone.number.size}")
	@NotBlank(message="{telephone.number.vazio}")
	private String number;
	
	@Column(name="telephone_type")
	@NotNull(message="{telephone.type.empty}")
	private TelephoneType telephoneType;
	
	@ManyToOne
	@JoinColumn(name = "person_id", nullable = false)
	private Person person;
	
	@Column(name = "register_date")
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date registerDate;
	
	@Column(name="operator_login")
	private String operatorLogin;
	
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

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public TelephoneType getTelephoneType() {
		return telephoneType;
	}

	public void setTelephoneType(TelephoneType telephoneType) {
		this.telephoneType = telephoneType;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
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
	
	
	
}
