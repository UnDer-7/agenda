package com.matus.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matus.agenda.domain.Anotacao;

@Repository
public interface AnotacaoRepository extends JpaRepository<Anotacao, Integer>{
	
}
