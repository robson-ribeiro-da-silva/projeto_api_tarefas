package com.projeto.apptarefas.domain.dto;

import java.util.ArrayList;
import java.util.List;


import com.projeto.apptarefas.domain.model.Departamento;
import com.projeto.apptarefas.domain.model.Tarefa;

public class PessoaDTO {
	
	private String nome;
	
	private String departamento;
	
	private int totalhorasgastas; 
	
	private int mediahorasgastas; 
	
	private List<String> tarefas = new ArrayList<String>();
	
	//private List<Tarefa> tarefas;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getTotalhorasgastas() {
		return totalhorasgastas;
	}

	public void setTotalhorasgastas(int totalhorasgastas) {
		this.totalhorasgastas = totalhorasgastas;
	}

	public int getMediahorasgastas() {
		return mediahorasgastas;
	}

	public void setMediahorasgastas(int mediahorasgastas) {
		this.mediahorasgastas = mediahorasgastas;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public List<String> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<String> tarefas) {
		this.tarefas = tarefas;
	}

//	public List<Tarefa> getTarefas() {
//		return tarefas;
//	}
//
//	public void setTarefas(List<Tarefa> tarefas) {
//		this.tarefas = tarefas;
//	}
	
	


}
