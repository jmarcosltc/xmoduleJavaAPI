package com.xmodule.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.xmodule.api.entity.Curso;

public interface CursoRepository extends MongoRepository<Curso, String> {

}
