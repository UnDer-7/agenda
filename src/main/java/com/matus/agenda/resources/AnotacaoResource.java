package com.matus.agenda.resources;

import com.matus.agenda.domain.Anotacao;
import com.matus.agenda.domain.Materia;
import com.matus.agenda.dto.AnotacaoDTO;
import com.matus.agenda.dto.AnotacaoNewDTO;
import com.matus.agenda.services.AnotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    /**
     * -----------JSON OBJECT-----------
    {
        "nomeAnotacao" : "FILHA DA PUTA",
            "materias" : [
        {
            "id": 1
        },
        {
            "id": 3
        }
		]
    }
    */
    public ResponseEntity<Void> insert(@Valid @RequestBody AnotacaoNewDTO objDTO) {
        Anotacao obj = anotacaoService.fromDTO(objDTO);
        anotacaoService.insert(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody AnotacaoNewDTO objDTO, @PathVariable Integer id) {
        Anotacao obj = anotacaoService.fromDTO(objDTO);
        obj.setId(id);
        obj = anotacaoService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        anotacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Anotacao>> findAll() {
        List<Anotacao> list = anotacaoService.findAll();
//        List<AnotacaoDTO> listDTO = list.stream().map(obj -> new AnotacaoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/page", method=RequestMethod.GET)
    public ResponseEntity<Page<AnotacaoDTO>> findPage(
//            anotacoes/page?page=0&linesPerPage = 20&order=prova dia 05/02 - Eng. II&direction=ASC
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nomeAnotacao") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<Anotacao> list = anotacaoService.findPage(page, linesPerPage, orderBy, direction);
        Page<AnotacaoDTO> listDTO = list.map(obj -> new AnotacaoDTO(obj));

        return ResponseEntity.ok().body(listDTO);
    }
}
