package br.com.renanlr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class AgendamentoEmail {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	@NotBlank(message="{agendamentoEmail.email.vazio}")
	@Email(message="{agendamentoEmail.email.invalido}")
	private String email;
	
	@Column
	@NotBlank(message="{agendamentoEmail.assunto.vazio}")
	private String assunto;

	@Column
	@NotBlank(message="{agendamentoEmail.mensagem.vazio}")
	private String mensagem;
	
	@Column
	private Boolean enviado;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Boolean getEnviado() {
		return enviado;
	}
	
	public void setEnviado(Boolean enviado) {
		this.enviado = enviado;
	}
	
	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String asssunto) {
		this.assunto = asssunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AgendamentoEmail other = (AgendamentoEmail) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
