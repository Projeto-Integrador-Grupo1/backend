package com.generation.zerone.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_categorias")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O campo Nome da Categoria é Obrigatório")
	@Size(min = 5, max = 100, message = "Tamanho mínimo: 5, Tamano máximo: 100")
	private String nomeCategoria;
	
	@NotBlank(message = "O campo Descrição é Obrigatório")
	@Size(min = 10, max = 1000, message = "Tamanho mínimo: 10, Tamano máximo: 1000")
	private String descricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoria() {
		return nomeCategoria;
	}

	public void setCategoria(String categoria) {
		this.nomeCategoria = categoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
