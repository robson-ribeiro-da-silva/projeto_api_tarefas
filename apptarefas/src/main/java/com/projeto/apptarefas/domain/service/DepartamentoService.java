package com.projeto.apptarefas.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.apptarefas.domain.dto.DepartamentoDTO;
import com.projeto.apptarefas.domain.model.Departamento;
import com.projeto.apptarefas.domain.repository.DepartamentoRepository;

@Service
public class DepartamentoService {
	
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	public List<DepartamentoDTO> findAll(){
		
		List<DepartamentoDTO> departamentos_dto = new ArrayList<>();
		List<Departamento> departamentos = departamentoRepository.findAll();
		
		for(Departamento departamento : departamentos) {
			DepartamentoDTO departamentoDTO = new DepartamentoDTO();
			departamentoDTO.setTitulo(departamento.getTitulo());
			departamentoDTO.setQuantidadepessoas(departamentoRepository.countDepartamentoInPessoaById(departamento.getId()));
			departamentoDTO.setQuantidadetarefas(departamentoRepository.countDepartamentoInTarefaById(departamento.getId()));
			
			departamentos_dto.add(departamentoDTO);
		}
		
		return departamentos_dto;
	}

}
