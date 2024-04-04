package com.generation.zerone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.zerone.model.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
	public List <Projeto> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);
}