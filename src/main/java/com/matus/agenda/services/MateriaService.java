package com.matus.agenda.services;

import java.util.List;
import java.util.Optional;

import com.matus.agenda.domain.Usuario;
import com.matus.agenda.security.UserSS;
import com.matus.agenda.services.exceptions.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.matus.agenda.domain.Materia;
import com.matus.agenda.dto.MateriaDTO;
import com.matus.agenda.repository.AnotacaoRepository;
import com.matus.agenda.repository.MateriaRepository;
import com.matus.agenda.services.exceptions.DataIntegrityException;
import com.matus.agenda.services.exceptions.ObjectNotFoundException;

@Service
public class MateriaService {

	@Autowired
	private MateriaRepository materiaRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private AnotacaoRepository anotacaoRepository;

	public Materia find(Integer id) {
		Optional<Materia> obj = materiaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Tipo: " + Materia.class.getName()));
	}

	public Materia insert(Materia obj) {
		obj.setId(null);
		materiaRepository.save(obj);
		anotacaoRepository.saveAll(obj.getAnotacoes());
		return obj;
	}

	public Materia update(Materia obj) {
		find(obj.getId());
		return materiaRepository.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			materiaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é posivel excluir uma Materia que possui Anotações");
		}
	}

	public List<Materia> findAll() {
		return materiaRepository.findAll();
	}

	public Page<Materia> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
	    UserSS user = UserService.authnticated();
	    if(user == null){
	        throw new AuthorizationException("Acesso Negado");
        }
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return materiaRepository.findAll(pageRequest);
	}

	public Materia fromDTO(MateriaDTO objDTO) {
		UserSS user = UserService.authnticated();
		Usuario usuario = usuarioService.find(user.getId());
		objDTO.setUsuario(usuario);
		return new Materia(objDTO.getId(), objDTO.getNomeMateria(), objDTO.getUsuario());
	}

//	public Materia fromDTO(AnotacaoNewDTO objDTO) {
//		Materia mat = new Materia(null, objDTO.getNomeMateria());
//		Anotacao ano = new Anotacao(null, objDTO.getNomeAnotacao(), Arrays.asList(mat));
//		mat.getAnotacoes().add(ano);
//		return mat;
//	}
}
