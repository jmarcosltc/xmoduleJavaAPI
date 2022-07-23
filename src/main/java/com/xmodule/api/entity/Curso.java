package com.xmodule.api.entity;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// Entity = model

@Document(collection = "curso")
public class Curso {

	@Id private String id;
	
	private String nome;
	
	private ArrayList<String> id_treinamentos;

	public Curso() {
	}

	public Curso(String nome, ArrayList<String> id_treinamentos) {
		this.nome = nome;
		this.id_treinamentos = id_treinamentos;
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

	public ArrayList<String> getId_treinamentos() {
		return id_treinamentos;
	}

	public void setId_treinamentos(ArrayList<String> id_treinamentos) {
		this.id_treinamentos = id_treinamentos;
	}
	
	
	
	
}
