package com.xmodule.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.xmodule.api.entity.Professor;

public interface ProfessorRepository extends MongoRepository<Professor, String> {

}
