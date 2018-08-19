package com.matus.agenda.services;

import com.matus.agenda.domain.Anotacao;
import com.matus.agenda.domain.Materia;
import com.matus.agenda.repository.AnotacaoRepository;
import com.matus.agenda.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private AnotacaoRepository anotacaoRepository;

    public void inseredeDadosNoBanco(){
        Materia mat1 = new Materia(null, "Eng. II");
        Materia mat2 = new Materia(null, "Projeto II");
        Materia mat3 = new Materia(null, "S.O.");
        Materia mat4 = new Materia(null, "LTP III");

        Anotacao ano1 = new Anotacao(null, "Trabalho dia 22/06 - Eng. II", Arrays.asList(mat1));
        Anotacao ano2 = new Anotacao(null, "prova dia 05/02 - Eng. II", Arrays.asList(mat1));
        Anotacao ano3 = new Anotacao(null, "Apresentação 11/11 - Eng. II, S.O.", Arrays.asList(mat1, mat3));
        Anotacao ano4 = new Anotacao(null, "Mini-Teste 09/06 - S.O.", Arrays.asList(mat3));

        mat1.getAnotacoes().addAll(Arrays.asList(ano1,ano2,ano2,ano3));
        mat3.getAnotacoes().addAll(Arrays.asList(ano3,ano4));

        materiaRepository.saveAll(Arrays.asList(mat1,mat2,mat3,mat4));
        anotacaoRepository.saveAll(Arrays.asList(ano1,ano2, ano3, ano4));
    }
}
