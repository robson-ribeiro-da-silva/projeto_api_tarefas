package com.projeto.apptarefas.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.apptarefas.domain.dto.DepartamentoDTO;
import com.projeto.apptarefas.domain.model.Departamento;
import com.projeto.apptarefas.domain.model.Pessoa;
import com.projeto.apptarefas.domain.repository.DepartamentoRepository;
import com.projeto.apptarefas.domain.service.DepartamentoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {
	
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@Autowired
	private DepartamentoService departamentoService;
	
	/**
	 * Método adiciona um novo Departamento
	 * @return
	 */
	@PostMapping("/post")
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary="Adiciona um novo departamento")
	public Departamento addDepartamento(@Valid @RequestBody Departamento departamento){
		
		return departamentoRepository.save(departamento);
	}
	
	/**
	 * Método que retorna uma lista de todos Departamentos
	 * @return
	 */
	@GetMapping("/getall")
	@Operation(summary="Lista todos departamentos")
	public  ResponseEntity<List<Departamento>> findAll(){
		
		return ResponseEntity.ok(departamentoRepository.findAll());
	}
	
	/**
	 * Método que retorna uma lista de Departamentos, total de pessoas e tarefas do Departamento
	 * @return
	 */
	@Operation(summary=" Retorna lista de departamentos com quantidade de pessoas e tarefas")
	@GetMapping("/get")
	public  ResponseEntity<List<DepartamentoDTO>> findByPessoasAndTarefas(){
		
		return ResponseEntity.ok(departamentoService.findAll());
	}
	
	/**
	 * Método que atualiza um Departamento existente
	 * @return
	 */
	@PutMapping("/put/{id}")
	@Operation(summary="Atualiza um departamento existente")
	public  ResponseEntity<Departamento> updateDepartamento(@Valid @PathVariable("id") Long id, @RequestBody Departamento departamento){
		
		if(!departamentoRepository.existsById(id)){
			return ResponseEntity.notFound().build();
		}
		departamento.setId(id);		
		return ResponseEntity.ok(departamentoRepository.save(departamento));
	}

}
