package com.matus.agenda.services;

import com.matus.agenda.domain.Anotacao;
import com.matus.agenda.domain.Materia;
import com.matus.agenda.domain.Usuario;
import com.matus.agenda.dto.AnotacaoNewDTO;
import com.matus.agenda.repository.AnotacaoRepository;
import com.matus.agenda.repository.MateriaRepository;
import com.matus.agenda.security.UserSS;
import com.matus.agenda.services.exceptions.DataIntegrityException;
import com.matus.agenda.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AnotacaoService {

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AnotacaoRepository anotacaoRepository;

    public Anotacao find(Integer id) {
        Optional<Anotacao> obj = anotacaoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! ID: " + id + ", Tipo: " + Anotacao.class.getName()));
    }

    public Anotacao insert(Anotacao obj) {
        obj.setId(null);
//        materiaRepository.saveAll(obj.getMaterias());
//        System.out.println("=======MATERIA========= "+obj.getMaterias().get(0).getNomeMateria());
        anotacaoRepository.save(obj);
        return obj;
    }

    public Anotacao update(Anotacao obj) {
        find(obj.getId());
        return anotacaoRepository.save(obj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            anotacaoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é foi possivel excluir!!");
        }
    }

    public List<Anotacao> findAll() {
        return anotacaoRepository.findAll();
    }

    public Page<Anotacao> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        return anotacaoRepository.findAll(pageRequest);
    }

    public Anotacao fromDTO(AnotacaoNewDTO objDTO) {
//        Materia materia = new Materia(objDTO.getIdMateria(), null);
        UserSS user = UserService.authnticated();
        Usuario usuario = usuarioService.find(user.getId());
        objDTO.setUsuario(usuario);
        Anotacao anotacao = new Anotacao(null, objDTO.getNomeAnotacao(), objDTO.getMaterias(), objDTO.getUsuario());
        return anotacao;
    }
}
