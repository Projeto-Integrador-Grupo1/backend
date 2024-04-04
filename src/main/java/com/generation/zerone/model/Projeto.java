package com.generation.zerone.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_projetos")
public class Projeto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O campo Título é obrigatório!")
	@Size(min = 2, max = 100, message = "Tamanho mínimo: 2, Tamano máximo: 100")
	private String titulo;
	
	@NotBlank(message = "O campo Descrição é obrigatório!")
	@Size(min = 10, max = 5000, message = "Tamanho mínimo: 10, Tamano máximo: 5000")
	private String descricao;
	
	private Integer qtdDoacoes;
	
	@Column(precision = 12, scale = 2)
	private BigDecimal valorAtual;
	
	@NotNull(message = "O Valor de Meta é obrigatório!")
	@Column(precision = 12, scale = 2) 
	private BigDecimal valorMeta;
	
	@NotNull(message = "Defina entre Imagem ou Vídeo!" )
	private String tipoMidia;
	
	@NotNull(message = "É obrigatório inserir pelo menos uma Imagem ou Vídeo!")
	private String linkMidia;
	
	@NotNull(message = "A Data Limite é obrigatória!")
	private LocalDate dataLimite;
	
	@UpdateTimestamp
	private LocalDateTime data;
	
	@ManyToOne
	@JsonIgnoreProperties("projeto")
	private Usuario usuario;
	
	@ManyToOne
	@JsonIgnoreProperties("projeto")
	private Categoria categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQtdDoacoes() {
		return qtdDoacoes;
	}

	public void setQtdDoacoes(Integer qtdDoacoes) {
		this.qtdDoacoes = qtdDoacoes;
	}

	public BigDecimal getValorAtual() {
		return valorAtual;
	}

	public void setValorAtual(BigDecimal valorAtual) {
		this.valorAtual = valorAtual;
	}

	public BigDecimal getValorMeta() {
		return valorMeta;
	}

	public void setValorMeta(BigDecimal valorMeta) {
		this.valorMeta = valorMeta;
	}

	public String getTipoMidia() {
		return tipoMidia;
	}

	public void setTipoMidia(String tipoMidia) {
		this.tipoMidia = tipoMidia;
	}

	public String getLinkMidia() {
		return linkMidia;
	}

	public void setLinkMidia(String linkMidia) {
		this.linkMidia = linkMidia;
	}

	public LocalDate getDataLimite() {
		return dataLimite;
	}

	public void setDataLimite(LocalDate dataLimite) {
		this.dataLimite = dataLimite;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	

}