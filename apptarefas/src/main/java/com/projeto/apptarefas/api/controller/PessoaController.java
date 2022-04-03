package com.projeto.apptarefas.api.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.apptarefas.domain.dto.PessoaDTO;
import com.projeto.apptarefas.domain.model.Pessoa;
import com.projeto.apptarefas.domain.repository.PessoaRepository;
import com.projeto.apptarefas.domain.service.PessoaService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private PessoaService pessoaService;
	
	/**
	 * Método que adiciona uma nova Pessoa
	 * @param pessoa - Parâmentro do tipo Pessoa
	 * @return
	 */
	@PostMapping("/post")
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary="Adiciona uma nova pessoa")
	public Pessoa addPessoa(@Valid @RequestBody Pessoa pessoa){
		
		return pessoaRepository.save(pessoa);
	}
	
	/**
	 * Método que retorna uma lista de Pessoas e suas Tarefas
	 * @return
	 */
	@GetMapping("/get")
	@Operation(summary="Listar pessoas e suas tarefas e horas gastas")
	public  ResponseEntity<List<PessoaDTO>> findByTotalHoras(){
		
		return ResponseEntity.ok(pessoaService.findTotalHorasGastasByTarefa());
	}
	
	/**
	 * Método que atualiza uma Pessoa existente apartir do identificador
	 * @param pessoa - Parâmentro do tipo Pessoa
	 * @param id - um Long referente ao identificador da Pessoa 
	 * @return
	 */
	@PutMapping("/put/{id}")
	@Operation(summary="Atualiza uma pessoa existente")
	public ResponseEntity<Pessoa> updatePessoa(@Valid @PathVariable("id") Long id, @RequestBody Pessoa pessoa){
		
		if(!pessoaRepository.existsById(id)){
			return ResponseEntity.notFound().build();
		}
		pessoa.setId(id);		
		return ResponseEntity.ok(pessoaRepository.save(pessoa));
	}
	
	/**
	 * Método que remove uma Pessoa existente apartir do identificador
	 * @param id - um Long referente ao identificador da Pessoa 
	 * @return
	 */
	@DeleteMapping("/delete/{id}")
	@Operation(summary="Remove uma pessoa")
	public ResponseEntity<Void> deletePessoa(@PathVariable("id") Long id){
		
		if(!pessoaRepository.existsById(id)){
			return ResponseEntity.notFound().build();
		}
		pessoaService.deletePessoaById(id);
		return ResponseEntity.noContent().build();
	}
	

	/**
	 * Método que retorna uma lista de todas Pessoas
	 * @return
	 */
	@GetMapping("/getall")
	@Operation(summary="Listar todas pessoas")
	public  ResponseEntity<List<Pessoa>> findAll(){
		
		return ResponseEntity.ok(pessoaRepository.findAll());
	}
	

	/**
	 * Método que busca Pessoas pelo nome
	 * @return
	 */
	@GetMapping("/get/gastos")
	@Operation(summary="Busca pessoas pelo nome")
	public  ResponseEntity<List<PessoaDTO>> findByNome(@RequestParam(value="nome") String nome){
		
		return ResponseEntity.ok(pessoaService.findMediaHorasGastasByTarefa(nome));
	}

}
