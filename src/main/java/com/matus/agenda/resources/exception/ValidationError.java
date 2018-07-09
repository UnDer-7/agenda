package com.matus.agenda.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
	private static final long serialVersionUID = 1L;

	List<FieldMessage> erros = new ArrayList<>();
	
	public ValidationError(Integer status, String msg, Long	timeStamp) {
		super(status, msg, timeStamp);
	}
	
	public List<FieldMessage> getErros() {
		return erros;
	}
	
	public void addError(String fieldNome, String msg) {
		erros.add(new FieldMessage(fieldNome, msg));
	}
}
