package com.example.blogpessoal.controller;

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

import com.example.blogpessoal.model.Tema;
import com.example.blogpessoal.repository.TemaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemaController {

	@Autowired
	private TemaRepository temaRepository;

	@GetMapping
	public ResponseEntity<List<Tema>> getAll() {
		return ResponseEntity.ok(temaRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Tema> getById(@PathVariable Long id) {
		return temaRepository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

	}

	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Tema>> getAllByDescricao(@PathVariable String descricao) {
		return ResponseEntity.ok(temaRepository.findAllByDescricaoContainingIgnoreCase(descricao));

	}

	@GetMapping("/{id}")
	public ResponseEntity<Tema> getById(@PathVariable Long id) {
		return temaRepository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

	}

	@PostMapping
	public ResponseEntity<Tema> create(@Valid @RequestBody Tema tema) {
		tema.setId(null);

		return ResponseEntity.status(HttpStatus.CREATED).body(temaRepository.save(tema));

	}

	@PutMapping
	public ResponseEntity<Tema> update(@Valid @RequestBody Tema tema) {
		return temaRepository.findById(tema.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(temaRepository.save(tema)))
				.orElse(ResponseEntity.notFound().build());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Tema> tema = temaRepository.findById(id);

		if (tema.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		temaRepository.deleteById(id);
	}
}
