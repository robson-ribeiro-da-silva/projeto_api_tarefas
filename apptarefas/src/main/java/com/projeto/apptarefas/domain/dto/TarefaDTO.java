package com.projeto.apptarefas.domain.dto;

import java.time.LocalDate;

import javax.persistence.ManyToOne;

import com.projeto.apptarefas.domain.model.Departamento;
import com.projeto.apptarefas.domain.model.Pessoa;

public class TarefaDTO {
	
private String titulo;
	
	//@NotBlank
	//@Size(max = 50)
	private String descricao;
	
	//@NotBlank
	//@Size(max = 50)
	private LocalDate prazo;
	
	@ManyToOne
	private Departamento departamento;
	
	//@NotBlank
	//@Size(max = 50)
	private int duracao;
	
	@ManyToOne
	private Pessoa pessoa;
		
	//@NotBlank
	//@Size(max = 50)
	private boolean finalizado;

}
