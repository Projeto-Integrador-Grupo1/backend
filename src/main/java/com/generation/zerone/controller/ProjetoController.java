package com.generation.zerone.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.zerone.model.Projeto;
import com.generation.zerone.repository.CategoriaRepository;
import com.generation.zerone.repository.ProjetoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/projetos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProjetoController {

	@Autowired
	private ProjetoRepository projetoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping("/all")
	public ResponseEntity<List<Projeto>> getAll() {
		return ResponseEntity.ok(projetoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Projeto> getById(@PathVariable Long id) {
		return projetoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@PostMapping
	public ResponseEntity<Projeto> post(@Valid @RequestBody Projeto projeto) {
		if(categoriaRepository.existsById(projeto.getCategoria().getId()))	
			return ResponseEntity.status(HttpStatus.CREATED)
				.body(projetoRepository.save(projeto));
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe", null);
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Projeto>>getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(projetoRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PutMapping
	public ResponseEntity<Projeto> put(@Valid @RequestBody Projeto projeto){
		if(projetoRepository.existsById(projeto.getId())){
			
			if(categoriaRepository.existsById(projeto.getCategoria().getId()))
				
				return ResponseEntity.status(HttpStatus.OK)
						.body(projetoRepository.save(projeto));
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe", null);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional <Projeto> projeto = projetoRepository.findById(id);
		
		if(projeto.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		projetoRepository.deleteById(id);
	}
}