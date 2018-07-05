package com.matus.agenda.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matus.agenda.domain.Materia;
import com.matus.agenda.repository.MateriaRepository;

@Service
public class MateriaService {
	
	@Autowired
	private MateriaRepository materiaRepository;

	public Materia find(Integer id) {
		Optional<Materia> obj = materiaRepository.findById(id);
		return obj.orElse(null);
	}
}
