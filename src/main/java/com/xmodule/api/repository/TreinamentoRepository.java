package com.xmodule.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xmodule.api.entity.Treinamento;

public interface TreinamentoRepository extends MongoRepository<Treinamento, String>{
	
}
