package com.matus.agenda.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.matus.agenda.domain.Materia;
import com.matus.agenda.dto.MateriaDTO;
import com.matus.agenda.dto.MateriaNewDTO;
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

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody MateriaNewDTO objDTO){
		Materia obj = materiaService.fromDTO(objDTO);
		obj = materiaService.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody MateriaDTO objDTO, @PathVariable Integer id){
		Materia obj = materiaService.fromDTO(objDTO);
		obj.setId(id);
		obj = materiaService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		materiaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<MateriaDTO>> findAll(){
		List<Materia> list = materiaService.findAll();
		List<MateriaDTO> listDTO = list.stream().map(obj -> new MateriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value = "/page", method=RequestMethod.GET)
	public ResponseEntity<Page<MateriaDTO>> findPage(
//			materias/page?page=0&linesPerPage = 20&order=LTP III&direction=ASC
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nomeMateria") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		
		Page<Materia> list = materiaService.findPage(page, linesPerPage, orderBy, direction);
		Page<MateriaDTO> listDTO =  list.map(obj -> new MateriaDTO(obj));
		
		return ResponseEntity.ok().body(listDTO);
	}
}










