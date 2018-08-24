package com.matus.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.matus.agenda.domain.Materia;

import java.util.List;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Integer>{

//    @Query("SELECT materia_id FROM anotacao_materia WHERE materia_id =  :#{#materia_id}")
//    List<Object> verificaTa(@Param("materia_id") Integer materia_id);
}
