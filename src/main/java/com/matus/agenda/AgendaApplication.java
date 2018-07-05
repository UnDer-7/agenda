package com.matus.agenda;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.matus.agenda.domain.Materia;
import com.matus.agenda.repository.MateriaRepository;

@SpringBootApplication
public class AgendaApplication implements CommandLineRunner{
	
	@Autowired
	private MateriaRepository materiaRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(AgendaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Materia mat1 = new Materia(null, "Eng. II");
		Materia mat2 = new Materia(null, "S.O.");
		
		materiaRepository.saveAll(Arrays.asList(mat1, mat2));
	}
}
