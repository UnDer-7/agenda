package com.matus.agenda.services;

import com.matus.agenda.domain.Anotacao;
import com.matus.agenda.domain.Materia;
import com.matus.agenda.domain.Usuario;
import com.matus.agenda.domain.enums.Perfil;
import com.matus.agenda.repository.AnotacaoRepository;
import com.matus.agenda.repository.MateriaRepository;
import com.matus.agenda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private AnotacaoRepository anotacaoRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void inseredeDadosNoBanco(){

        Usuario u1 = new Usuario("Mateus",pe.encode("123"));

        Usuario u2 = new Usuario("UnDer7",pe.encode("123"));

        Usuario u3 = new Usuario("admin",pe.encode("admin"));
        u3.addPerfil(Perfil.ADMIN);
        usuarioRepository.saveAll(Arrays.asList(u1,u2,u3));

        Materia mat1 = new Materia(null, "Eng. II", u1);
        Materia mat2 = new Materia(null, "Projeto II",u2);
        Materia mat3 = new Materia(null, "S.O.",u1);
        Materia mat4 = new Materia(null, "LTP III",u2);

        Anotacao ano1 = new Anotacao(null, "Trabalho dia 22/06 - Eng. II", Arrays.asList(mat1),u1);
        Anotacao ano2 = new Anotacao(null, "prova dia 05/02 - Eng. II", Arrays.asList(mat1),u2);
        Anotacao ano3 = new Anotacao(null, "Apresentação 11/11 - Eng. II, S.O.", Arrays.asList(mat1, mat3),u2);
        Anotacao ano4 = new Anotacao(null, "Mini-Teste 09/06 - S.O.", Arrays.asList(mat3),u1);
        Anotacao ano5 = new Anotacao(null, "Consulta dia 21/08", null,u2);
        Anotacao ano6 = new Anotacao(null, "Pegar certificado TOEIC ", null, u2);
        Anotacao ano7 = new Anotacao(null, "Comprar Mangas", null,u2);

        mat1.getAnotacoes().addAll(Arrays.asList(ano1,ano2,ano2,ano3));
        mat3.getAnotacoes().addAll(Arrays.asList(ano3,ano4));

        materiaRepository.saveAll(Arrays.asList(mat1,mat2,mat3,mat4));
        anotacaoRepository.saveAll(Arrays.asList(ano1,ano2, ano3, ano4, ano5, ano6, ano7));
    }
}
