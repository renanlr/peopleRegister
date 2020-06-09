package br.com.renanlr.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.renanlr.enums.TelephoneType;

@Entity
public class Telephone {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	@Size(min = 3, max = 3, message="{}")
	@NotBlank(message="{}")
	private String ddd;
	
	@Column
	@Size(min = 8, max = 10, message="{}")
	@NotBlank(message="{}")
	private String number;
	
	@Column(name="telephone_type")
	private TelephoneType telephoneType;
	
	@ManyToOne
	@JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
	private Person person;
	
	@Column(name = "register_date")
	private Date registerDate;
	
	@Column(name="operator_login")
	private String operatorLogin;
	
	@PrePersist
	void registerDate() {
		java.util.Date dt = new java.util.Date();
		this.registerDate = new Date(dt.getTime());
	}
	
}
