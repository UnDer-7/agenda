package com.matus.agenda.dto;

import java.io.Serializable;

import com.matus.agenda.domain.Materia;

public class MateriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nomeMateria;

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
}
