package com.projeto.apptarefas.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projeto.apptarefas.domain.model.Pessoa;
import com.projeto.apptarefas.domain.model.Tarefa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	List<Pessoa> findByNomeContaining(String nome);
	
	@Query(value = "select p.* from pessoa p where p.nome like ?1%", nativeQuery = true)
	List<Pessoa> findByLikeNome(String nome);

}
