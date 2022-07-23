package com.xmodule.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xmodule.api.entity.Curso;
import com.xmodule.api.repository.CursoRepository;

@RestController
@RequestMapping("/v1")
public class CursoController {
	
	@Autowired
	private CursoRepository repository;
	
	@GetMapping("/cursos")
	public List<Curso> getCursos() {
		List<Curso> cursos = repository.findAll();
		return cursos;
	}
	
	@GetMapping("/curso/{id}")
	public ResponseEntity<Curso> getCurso(
			@PathVariable 
			String id) {
		Optional<Curso> curso = repository.findById(id);
		  	if (curso.isPresent()) {
		  		return new ResponseEntity<>(curso.get(), HttpStatus.OK);
		  	} else {
		  		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping(value = "/curso", consumes = {"application/xml","application/json"})
	public ResponseEntity<Curso> createCurso(
			@RequestBody Curso curso) {
		try {
			Curso _curso = repository.save(new Curso(curso.getNome(), curso.getId_treinamentos()));
		    return new ResponseEntity<>(_curso, HttpStatus.CREATED);
		  } catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	}
	
	@PutMapping("/curso/{id}")
	public ResponseEntity<Curso> updateCurso(
			@PathVariable("id") String id,
			@RequestBody Curso curso) {
		
		Optional<Curso> cursoData = repository.findById(id);
		if (cursoData.isPresent()) {
			Curso _curso = cursoData.get();
			_curso.setNome(curso.getNome());
			_curso.setId_treinamentos(curso.getId_treinamentos());
			return new ResponseEntity<>(repository.save(_curso), HttpStatus.OK);
			
		} else {
			return new ResponseEntity<Curso>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@DeleteMapping("/curso/{id}")
	public ResponseEntity<HttpStatus> deleteCurso(
			@PathVariable("id") String id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
