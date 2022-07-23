package com.xmodule.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xmodule.api.entity.Aluno;

public interface AlunoRepository extends MongoRepository<Aluno, String> {

}
