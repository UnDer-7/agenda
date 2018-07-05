package com.matus.agenda.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.matus.agenda.domain.Materia;
import com.matus.agenda.services.MateriaService;

@RestController
@RequestMapping(value = "/materias")
public class MateriaResource {
	
	@Autowired
	private MateriaService materiaService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id){
		Materia obj = materiaService.find(id);
		return ResponseEntity.ok().body(obj);
	}
}
