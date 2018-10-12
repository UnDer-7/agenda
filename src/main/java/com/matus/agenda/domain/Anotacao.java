package com.matus.agenda.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Anotacao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nomeAnotacao;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "anotacao_materia",
    joinColumns = @JoinColumn(name = "anotacao_id"), inverseJoinColumns = @JoinColumn(name = "materia_id"))
    private List<Materia> materias;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Anotacao() {
    }

    public Anotacao(Integer id, String nomeAnotacao, List<Materia> materia, Usuario usuario) {
        super();
        this.id = id;
        this.nomeAnotacao = nomeAnotacao;
        this.materias = materia;
        this.usuario = usuario;
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

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
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
        Anotacao other = (Anotacao) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Anotacao{");
        sb.append("id=").append(id);
        sb.append(", nomeAnotacao='").append(nomeAnotacao).append('\'');
        sb.append(", materias=").append(materias);
        sb.append('}');
        return sb.toString();
    }
}
