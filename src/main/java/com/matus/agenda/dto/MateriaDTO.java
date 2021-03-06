package com.matus.agenda.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.matus.agenda.domain.Materia;
import com.matus.agenda.domain.Usuario;

public class MateriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotEmpty(message="Campo obrigatorio")
	private String nomeMateria;

    private Usuario usuario;

	public MateriaDTO() {
	}

	public MateriaDTO(Materia obj) {
		this.id = obj.getId();
		this.nomeMateria = obj.getNomeMateria();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeMateria() {
		return nomeMateria;
	}

	public void setNomeMateria(String nomeMateria) {
		this.nomeMateria = nomeMateria;
	}

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
