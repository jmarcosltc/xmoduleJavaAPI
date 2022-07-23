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

import com.xmodule.api.entity.Aluno;
import com.xmodule.api.repository.AlunoRepository;


@RestController
@RequestMapping("/v1")
public class AlunoController {

	@Autowired
	private AlunoRepository repository;
	
	@GetMapping("/alunos")
	public List<Aluno> getAlunos() {
		List<Aluno> alunos = repository.findAll();
		return alunos;
	}
	
	@GetMapping("/aluno/{id}")
	public ResponseEntity<Aluno> getAluno(
			@PathVariable 
			String id) {
		Optional<Aluno> aluno = repository.findById(id);
		  	if (aluno.isPresent()) {
		  		return new ResponseEntity<>(aluno.get(), HttpStatus.OK);
		  	} else {
		  		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping(value = "/aluno", consumes = {"application/xml","application/json"})
	public ResponseEntity<Aluno> createAluno(
			@RequestBody Aluno aluno) {
		try {
		    Aluno _aluno = repository.save(
		    		new Aluno(aluno.getNome(), aluno.getEmail(),
		    				aluno.getTelefone(), aluno.getId_curso()));
		    return new ResponseEntity<>(_aluno, HttpStatus.CREATED);
		  } catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	}
	
	@PutMapping("/aluno/{id}")
	public ResponseEntity<Aluno> updateAluno(
			@PathVariable("id") String id,
			@RequestBody Aluno aluno) {
		
		Optional<Aluno> alunoData = repository.findById(id);
		if (alunoData.isPresent()) {
			Aluno _aluno = alunoData.get();
			_aluno.setNome(aluno.getNome());
			_aluno.setEmail(aluno.getEmail());
			_aluno.setTelefone(aluno.getTelefone());
			_aluno.setId_curso(aluno.getId_curso());
			return new ResponseEntity<>(repository.save(_aluno), HttpStatus.OK);
			
		} else {
			return new ResponseEntity<Aluno>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@DeleteMapping("/aluno/{id}")
	public ResponseEntity<HttpStatus> deleteAluno(
			@PathVariable("id")String id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
	

