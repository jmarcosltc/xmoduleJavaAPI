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
import com.xmodule.api.entity.Treinamento;
import com.xmodule.api.repository.TreinamentoRepository;


@RestController
@RequestMapping("/v1")
public class TreinamentoController {

	@Autowired
	private TreinamentoRepository repository;
	
	@GetMapping("/treinamentos")
	public List<Treinamento> getTreinamentos() {
		List<Treinamento> treinamentos = repository.findAll();
		return treinamentos;
	}
	
	@GetMapping("/treinamento/{id}")
	public ResponseEntity<Treinamento> getTreinamento(
			@PathVariable 
			String id) {
		Optional<Treinamento> treinamento = repository.findById(id);
		  	if (treinamento.isPresent()) {
		  		return new ResponseEntity<>(treinamento.get(), HttpStatus.OK);
		  	} else {
		  		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping(value = "/treinamento", consumes = {"application/xml","application/json"})
	public ResponseEntity<Treinamento> createTreinamento(
			@RequestBody Treinamento treinamento) {
		try {
			Treinamento _treinamento = repository.save(new Treinamento(treinamento.getNome(), treinamento.getMinutos()));
		    return new ResponseEntity<>(_treinamento, HttpStatus.CREATED);
		  } catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	}
	
	@PutMapping("/treinamento/{id}")
	public ResponseEntity<Treinamento> updateTreinamento(
			@PathVariable("id") String id,
			@RequestBody Treinamento treinamento) {
		
		Optional<Treinamento> treinamentoData = repository.findById(id);
		if (treinamentoData.isPresent()) {
			Treinamento _treinamento = treinamentoData.get();
			_treinamento.setNome(treinamento.getNome());
			_treinamento.setMinutos(treinamento.getMinutos());
			return new ResponseEntity<>(repository.save(_treinamento), HttpStatus.OK);
			
		} else {
			return new ResponseEntity<Treinamento>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@DeleteMapping("/treinamento/{id}")
	public ResponseEntity<HttpStatus> deleteCTreinamento(
			@PathVariable("id") String id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
