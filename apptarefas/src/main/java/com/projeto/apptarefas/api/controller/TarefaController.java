package com.projeto.apptarefas.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.apptarefas.domain.model.Pessoa;
import com.projeto.apptarefas.domain.model.Tarefa;
import com.projeto.apptarefas.domain.repository.PessoaRepository;
import com.projeto.apptarefas.domain.repository.TarefaRepository;
import com.projeto.apptarefas.domain.service.PessoaService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	/**
	 * Método que adiciona uma nova Tarefa
	 * @param - Parâmentro do tipo Tarefa
	 * @return
	 */
	@PostMapping("/post")
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary="Adiciona uma nova tarefa")
	public Tarefa addTarefa(@Valid @RequestBody Tarefa tarefa){
		
		return tarefaRepository.save(tarefa);
	}
	
	/**
	 * Método que retorna uma lista de todas Tarefas
	 * @return
	 */
	@GetMapping("/get")
	@Operation(summary="Lista todas tarefas")
	public  ResponseEntity<List<Tarefa>> findAll(){
		
		return ResponseEntity.ok(tarefaRepository.findAll());
	}
	
	/**
	 * Método que retorna uma lista de 3 Tarefas pendentes
	 * @return
	 */
	@GetMapping("/get/tarefas/pendentes")
	@Operation(summary="Lista 3 tarefas pendentes")
	public  ResponseEntity<List<Tarefa>> findTarefasPendentes(){
		
		return ResponseEntity.ok(tarefaRepository.findByTarefaPendente(3));
	}
	
	/**
	 * Método que aloca uma Pessoa a uma Tarefa existente  apartir do identificador
	 * @param id - um Long referente ao identificador da Pessoa
	 * @return
	 */
	@PutMapping("/put/tarefas/alocar/{id}")
	@Operation(summary="Aloca uma pessoa aum tarefa")
	public ResponseEntity<Tarefa> alocarTarefa(@Valid @PathVariable("id") Long id){
		
		if(!pessoaRepository.existsById(id)){
			return ResponseEntity.notFound().build();
		}
		Tarefa tarefa = pessoaService.alocarPessoa(id);
		if(tarefa.getId() == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(tarefa);
	}
	
	/**
	 * Método que atualiza uma Tarefa existente para Finalizada apartir do identificador
	 * @param id - um Long referente ao identificador da Tarefa 
	 * @return
	 */
	@PutMapping("/put/tarefas/finalizar/{id}")
	@Operation(summary="Atualiza a tarefa como finalizada")
	public ResponseEntity<Tarefa> updateTarefa(@Valid @PathVariable("id") Long id){
		
		if(!tarefaRepository.existsById(id)){
			return ResponseEntity.notFound().build();
		}
		Tarefa tarefa = tarefaRepository.getById(id);
		tarefa.setFinalizado(true);
		return ResponseEntity.ok(tarefaRepository.save(tarefa));
	}
	
	/**
	 * Método que remove uma Tarefa existente apartir do identificador
	 * @param id - um Long referente ao identificador da Pessoa 
	 * @return http status: 404 ou 204
	 */
	@DeleteMapping("/delete/{id}")
	@Operation(summary="Remove uma tarefa")
	public ResponseEntity<Void> deleteTarefa(@PathVariable("id") Long id){
		
		if(!tarefaRepository.existsById(id)){
			return ResponseEntity.notFound().build();
		}
		tarefaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
