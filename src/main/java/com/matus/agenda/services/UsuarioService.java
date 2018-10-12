package com.matus.agenda.services;

import com.matus.agenda.domain.Usuario;
import com.matus.agenda.domain.enums.Perfil;
import com.matus.agenda.repository.UsuarioRepository;
import com.matus.agenda.security.UserSS;
import com.matus.agenda.services.exceptions.AuthorizationException;
import com.matus.agenda.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    public Usuario find(Integer id){
        UserSS user = UserService.authnticated();
        if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())){
            throw new AuthorizationException("Você não possui permisão para ver outros usuarios");
        }
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto nao encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
    }

    public Usuario insert(Usuario usuario){
        usuario.setId(null);
        usuario.addPerfil(Perfil.COMMON);
        usuario.setSenha(pe.encode(usuario.getSenha()));
        usuarioRepository.save(usuario);
        return usuario;
    }

    public Usuario update(Usuario usuario){
        find(usuario.getId());
        return usuarioRepository.save(usuario);
    }

    public void delete(Integer id){
        find(id);
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }
}
