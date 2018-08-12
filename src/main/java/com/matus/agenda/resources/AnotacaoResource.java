package com.matus.agenda.resources;

import com.matus.agenda.domain.Anotacao;
import com.matus.agenda.domain.Materia;
import com.matus.agenda.dto.AnotacaoDTO;
import com.matus.agenda.dto.MateriaDTO;
import com.matus.agenda.dto.MateriaNewDTO;
import com.matus.agenda.services.AnotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/anotacoes")
public class AnotacaoResource {

    @Autowired
    AnotacaoService anotacaoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Anotacao obj = anotacaoService.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody AnotacaoDTO objDTO) {
        Anotacao obj = anotacaoService.fromDTO(objDTO);
        obj = anotacaoService.insert(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody AnotacaoDTO objDTO, @PathVariable Integer id){
        Anotacao obj = anotacaoService.fromDTO(objDTO);
        obj.setId(id);
        obj = anotacaoService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        anotacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<AnotacaoDTO>> findAll(){
        List<Anotacao> list = anotacaoService.findAll();
        List<AnotacaoDTO> listDTO = list.stream().map(obj -> new AnotacaoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
}
