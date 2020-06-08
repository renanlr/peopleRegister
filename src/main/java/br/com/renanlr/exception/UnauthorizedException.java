package br.com.renanlr.exception;

import java.util.ArrayList;
import java.util.List;


public class UnauthorizedException extends Exception{
	
	private List<String> messages;
	
	public UnauthorizedException() {
		super();
		messages = new ArrayList<>();
	}

	public UnauthorizedException(String message) {
		super(message);
		messages = new ArrayList<>();
		messages.add(message);
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(String message) {
		this.messages.add(message);
	}

}
