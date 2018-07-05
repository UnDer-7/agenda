package com.matus.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matus.agenda.domain.Materia;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Integer>{
	
}
