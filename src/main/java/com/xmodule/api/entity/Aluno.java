package com.xmodule.api.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "aluno")
public class Aluno {

	@Id
	private String id;
	
	private String nome;
	private String email;
	private String telefone;
	
	
	private String id_curso;
	
	public Aluno(String nome, String email, String telefone, String id_curso) {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.id_curso = id_curso;
	}

	public Aluno() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getId_curso() {
		return id_curso;
	}

	public void setId_curso(String id_curso) {
		this.id_curso = id_curso;
	}

	
	
	
}
