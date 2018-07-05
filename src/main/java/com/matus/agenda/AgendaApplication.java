package com.matus.agenda;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.matus.agenda.domain.Anotacao;
import com.matus.agenda.domain.Materia;
import com.matus.agenda.repository.AnotacaoRepository;
import com.matus.agenda.repository.MateriaRepository;

@SpringBootApplication
public class AgendaApplication implements CommandLineRunner{
	
	@Autowired
	private MateriaRepository materiaRepository;
	
	@Autowired
	private AnotacaoRepository anotacaoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(AgendaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Materia mat1 = new Materia(null, "Eng. II");
		Materia mat2 = new Materia(null, "S.O.");
		
		Anotacao ano1 = new Anotacao(null, "Trabalho dia 22/06 - Eng. II", mat1);
		Anotacao ano2 = new Anotacao(null, "prova dia 05/02 - Eng. II", mat1);
		Anotacao ano3 = new Anotacao(null, "Apresentação 11/11 - Eng. II, S.O.", mat1);
		Anotacao ano4 = new Anotacao(null, "Apresentação 11/11 - Eng. II, S.O.", mat2);
		Anotacao ano5 = new Anotacao(null, "Mini-Teste 09/06 - S.O.", mat2);		
		
		mat1.getAnotacoes().addAll(Arrays.asList(ano1,ano2,ano2,ano3));
		mat2.getAnotacoes().addAll(Arrays.asList(ano4,ano5));
		
		materiaRepository.saveAll(Arrays.asList(mat1,mat2));
		anotacaoRepository.saveAll(Arrays.asList(ano1,ano2, ano3, ano4, ano5));
	}
}
