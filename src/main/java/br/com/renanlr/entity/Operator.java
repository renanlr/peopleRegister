package br.com.renanlr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Operator {
	
	@Column
	private String nome;
	
	@Column
	private String login;
	
	@Column
	private String password;
}
