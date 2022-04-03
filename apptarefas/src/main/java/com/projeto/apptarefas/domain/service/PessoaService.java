package com.projeto.apptarefas.domain.service;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.apptarefas.domain.dto.PessoaDTO;
import com.projeto.apptarefas.domain.model.Pessoa;
import com.projeto.apptarefas.domain.model.Tarefa;
import com.projeto.apptarefas.domain.repository.PessoaRepository;
import com.projeto.apptarefas.domain.repository.TarefaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	public void deletePessoaById(Long id) {
		
		Pessoa pessoa = pessoaRepository.findById(id).get();
		
		if(!pessoa.getTarefas().isEmpty()) {
			for(Tarefa tarefa : pessoa.getTarefas()) {
				tarefa.setPessoa(null);
				tarefaRepository.save(tarefa);
			}
			
		}
		pessoaRepository.deleteById(id);
		
	}
	
	public List<PessoaDTO> findTotalHorasGastasByTarefa(){
		
		List<PessoaDTO> pessoas_horas_gastas = new ArrayList<>();
		List<Pessoa> pessoas = pessoaRepository.findAll();
		
		if(!pessoas.isEmpty()) {
			for(Pessoa pessoa : pessoas) {
				int total_tarefas = 0;
				int totalhorasgastas = 0;
				PessoaDTO pessoaDTO =  new PessoaDTO();
				if(!pessoa.getTarefas().isEmpty()) {
					for(Tarefa tarefa : pessoa.getTarefas()) {
						total_tarefas ++;
						totalhorasgastas += tarefa.getDuracao();
						pessoaDTO.getTarefas().add(tarefa.getTitulo());
					}
					pessoaDTO.setMediahorasgastas(totalhorasgastas / total_tarefas);
				}
				pessoaDTO.setNome(pessoa.getNome());
				pessoaDTO.setDepartamento(pessoa.getDepartamento().getTitulo());
				pessoaDTO.setTotalhorasgastas(totalhorasgastas);
				
				
				pessoas_horas_gastas.add(pessoaDTO);
			}
		}
		
		return pessoas_horas_gastas;
	}
	
	public List<PessoaDTO> findMediaHorasGastasByTarefa(String nome){
		
		List<PessoaDTO> pessoas_horas_gastas = new ArrayList<>();
		List<Pessoa> pessoas = pessoaRepository.findByLikeNome(nome);
		
		if(!pessoas.isEmpty()) {
			for(Pessoa pessoa : pessoas) {
				int totalhorasgastas = 0;
				int total_tarefas = 0;
				PessoaDTO pessoaDTO =  new PessoaDTO();
				if(!pessoa.getTarefas().isEmpty()) {
					for(Tarefa tarefa : pessoa.getTarefas()) {
						total_tarefas ++;
						totalhorasgastas += tarefa.getDuracao();
						pessoaDTO.getTarefas().add(tarefa.getTitulo());
					}
					pessoaDTO.setMediahorasgastas(totalhorasgastas / total_tarefas);
				}
				
				pessoaDTO.setNome(pessoa.getNome());
				pessoaDTO.setDepartamento(pessoa.getDepartamento().getTitulo());
				pessoaDTO.setTotalhorasgastas(totalhorasgastas);
				
				
				pessoas_horas_gastas.add(pessoaDTO);
			}
			
		}		
		return pessoas_horas_gastas;
	}
	
	public Tarefa alocarPessoa(Long id) {
		
		Pessoa pessoa = pessoaRepository.findById(id).get();
		
		List<Tarefa> tarefas = tarefaRepository.findByPessoaIsNull();
		Tarefa tarefa_aux = new Tarefa();
		for(Tarefa tarefa : tarefas) {
			
			if(tarefa.getDepartamento() != null && tarefa.getDepartamento().getId().equals(pessoa.getDepartamento().getId())) {
				tarefa.setPessoa(pessoa);
				tarefa_aux = tarefaRepository.save(tarefa);
				return tarefa_aux;
			}
		}
		return tarefa_aux;
	}

}
