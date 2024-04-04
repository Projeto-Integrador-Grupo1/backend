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
import com.generation.zerone.repository.ProjetoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/projetos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProjetoController {

	@Autowired
	private ProjetoRepository projetoRepository;
	
	@GetMapping
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
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(projetoRepository.save(projeto));
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Projeto>>getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(projetoRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PutMapping
	public ResponseEntity<Projeto> put(@Valid @RequestBody Projeto projeto){
		return projetoRepository.findById(projeto.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK)
						.body(projetoRepository.save(projeto)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
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