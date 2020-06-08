package br.com.renanlr.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MensagemErroDto {
	
	private List<String> mensagens;
	
	private Date dataErro;

	public List<String> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<String> mensagens) {
		this.mensagens = mensagens;
	}

	public Date getDataErro() {
		return dataErro;
	}

	public void setDataErro(Date dataErro) {
		this.dataErro = dataErro;
	}
	
	public static MensagemErroDto build(List<String> mensagem) {
		MensagemErroDto mensagemErroDto = new MensagemErroDto();
		mensagemErroDto.setMensagens(mensagem);
		mensagemErroDto.setDataErro(new Date());
		
		return mensagemErroDto;
	}
	
	public static MensagemErroDto build(String mensagem) {
		MensagemErroDto mensagemErroDto = new MensagemErroDto();
		ArrayList<String> mensagens = new ArrayList<String>();
		mensagens.add(mensagem);
		mensagemErroDto.setMensagens(mensagens);
		mensagemErroDto.setDataErro(new Date());
		
		return mensagemErroDto;
	}

}
