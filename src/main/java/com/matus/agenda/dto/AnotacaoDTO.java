package com.matus.agenda.dto;

import com.matus.agenda.domain.Anotacao;
import com.matus.agenda.domain.Materia;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;

public class AnotacaoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message="Campo obrigatorio")
	private String nomeAnotacao;

	private List<Materia> materias;

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

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("id: ");
        sb.append(getId());
        sb.append("\n");
        sb.append("Nome Anotação: ");
        sb.append(getNomeAnotacao());
        sb.append("\n");
        sb.append("Nome Materia: ");
        for(int i = 0; i < materias.size(); i++){
            sb.append(materias.get(i).getNomeMateria()+" --  ");
        }
        return sb.toString();
    }
}
