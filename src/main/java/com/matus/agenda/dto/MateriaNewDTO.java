package com.matus.agenda.dto;

import java.io.Serializable;

public class MateriaNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String nomeAnotacao;
	private String nomeMateria;
	
	public MateriaNewDTO() {
	}

	public String getNomeAnotacao() {
		return nomeAnotacao;
	}

	public void setNomeAnotacao(String nomeAnotacao) {
		this.nomeAnotacao = nomeAnotacao;
	}

	public String getNomeMateria() {
		return nomeMateria;
	}

	public void setNomeMateria(String nomeMateria) {
		this.nomeMateria = nomeMateria;
	}
	
	
}
