package com.projeto.apptarefas.domain.dto;

public class DepartamentoDTO {
	
	private String titulo;

	private int quantidadepessoas;
	
	private int quantidadetarefas;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getQuantidadepessoas() {
		return quantidadepessoas;
	}

	public void setQuantidadepessoas(int quantidadepessoas) {
		this.quantidadepessoas = quantidadepessoas;
	}

	public int getQuantidadetarefas() {
		return quantidadetarefas;
	}

	public void setQuantidadetarefas(int quantidadetarefas) {
		this.quantidadetarefas = quantidadetarefas;
	}
	
	
}
