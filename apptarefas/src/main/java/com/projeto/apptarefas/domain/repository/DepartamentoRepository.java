package com.projeto.apptarefas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projeto.apptarefas.domain.model.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long>{
	
	@Query(value = "select count(*) from pessoa where departamento_id = ?1", nativeQuery = true)
	int countDepartamentoInPessoaById(Long id);
	
	@Query(value = "select count(*) from tarefa where departamento_id = ?1", nativeQuery = true)
	int countDepartamentoInTarefaById(Long id);

}
