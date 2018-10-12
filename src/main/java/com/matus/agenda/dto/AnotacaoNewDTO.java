package com.matus.agenda.dto;

import com.matus.agenda.domain.Materia;
import com.matus.agenda.domain.Usuario;

import java.io.Serializable;
import java.util.List;

public class AnotacaoNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String nomeAnotacao;
    private List<Materia> materias;

    private Usuario usuario;
	
	public AnotacaoNewDTO() {
	}

	public String getNomeAnotacao() {
		return nomeAnotacao;
	}

	public void setNomeAnotacao(String nomeAnotacao) {
		this.nomeAnotacao = nomeAnotacao;
	}

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
