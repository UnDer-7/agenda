package com.matus.agenda.dto;

import com.matus.agenda.domain.Anotacao;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class AnotacaoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message="Campo obrigatorio")
	private String nomeAnotacao;

	public AnotacaoDTO() {
	}

	public AnotacaoDTO(Integer id, String nomeAnotacao) {
		super();
		this.id = id;
		this.nomeAnotacao = nomeAnotacao;
	}
	public AnotacaoDTO(Anotacao obj){
		this.id = obj.getId();
		this.nomeAnotacao = obj.getNomeAnotacao();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeAnotacao() {
		return nomeAnotacao;
	}

	public void setNomeAnotacao(String nomeAnotacao) {
		this.nomeAnotacao = nomeAnotacao;
	}
	
	
}
