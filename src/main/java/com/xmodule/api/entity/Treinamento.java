package com.xmodule.api.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "treinamento")
public class Treinamento {

	@Id
	private String id;
	
	private String nome;
	private Integer minutos;
	
	public Treinamento() {
		
	}
	
	public Treinamento(String nome, Integer minutos) {
		this.nome = nome;
		this.minutos = minutos;
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
	public Integer getMinutos() {
		return minutos;
	}
	public void setMinutos(Integer minutos) {
		this.minutos = minutos;
	}
	
	
	
}
