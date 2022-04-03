package com.projeto.apptarefas.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projeto.apptarefas.domain.model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
	
	List<Tarefa> findByPessoaIsNull();
	
	@Query(value = "select * from tarefa where pessoa_id is null order by prazo asc limit ?1", nativeQuery = true)
	List<Tarefa> findByTarefaPendente(int limit);

}
