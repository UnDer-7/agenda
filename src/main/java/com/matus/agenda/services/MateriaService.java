package com.matus.agenda.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.matus.agenda.domain.Materia;
import com.matus.agenda.repository.MateriaRepository;
import com.matus.agenda.services.exceptions.DataIntegrityException;
import com.matus.agenda.services.exceptions.ObjectNotFoundException;

@Service
public class MateriaService {
	
	@Autowired
	private MateriaRepository materiaRepository;

	public Materia find(Integer id) {
		Optional<Materia> obj = materiaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: "+id+", Tipo: "+ Materia.class.getName()));
	}
	
	public Materia insert(Materia obj) {
		obj.setId(null);
		return materiaRepository.save(obj);
	}
	
	public Materia update(Materia obj) {
		find(obj.getId());
		return materiaRepository.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try{
			materiaRepository.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é posivel excluir uma Materia que possui Anotações");
		}
	}
}
