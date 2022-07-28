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
import com.xmodule.api.entity.Professor;
import com.xmodule.api.repository.ProfessorRepository;

@RestController
@RequestMapping("/v1")
public class ProfessorController {

	@Autowired
	private ProfessorRepository repository;
	
	@GetMapping("/professores")
	public List<Professor> getAlunos() {
		List<Professor> professores = repository.findAll();
		return professores;
	}
	
	@GetMapping("/professor/{id}")
	public ResponseEntity<Professor> getProfessor(
			@PathVariable 
			String id) {
		Optional<Professor> professor = repository.findById(id);
		  	if (professor.isPresent()) {
		  		return new ResponseEntity<>(professor.get(), HttpStatus.OK);
		  	} else {
		  		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping(value = "/professor", consumes = {"application/xml","application/json"})
	public ResponseEntity<Professor> createProfessor(
			@RequestBody Professor professor) {
		try {
			Professor _professor = repository.save(
		    		new Professor(professor.getNome(), professor.getEmail(),
		    				professor.getTelefone(), professor.getId_curso()));
		    return new ResponseEntity<>(_professor, HttpStatus.CREATED);
		  } catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	}
	
	@PutMapping("/professor/{id}")
	public ResponseEntity<Professor> updateProfessor(
			@PathVariable("id") String id,
			@RequestBody Professor professor) {
		
		Optional<Professor> professorData = repository.findById(id);
		if (professorData.isPresent()) {
			Professor _professor = professorData.get();
			_professor.setNome(professor.getNome());
			_professor.setEmail(professor.getEmail());
			_professor.setTelefone(professor.getTelefone());
			_professor.setId_curso(professor.getId_curso());
			return new ResponseEntity<>(repository.save(_professor), HttpStatus.OK);
			
		} else {
			return new ResponseEntity<Professor>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@DeleteMapping("/professor/{id}")
	public ResponseEntity<HttpStatus> deleteProfessor(
			@PathVariable("id")String id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
