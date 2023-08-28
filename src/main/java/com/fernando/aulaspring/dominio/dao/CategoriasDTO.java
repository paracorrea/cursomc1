package com.fernando.aulaspring.dominio.dao;

import java.io.Serializable;


import javax.validation.constraints.NotBlank;


import org.hibernate.validator.constraints.Length;

import com.fernando.aulaspring.dominio.Categorias;

public class CategoriasDTO  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	Integer id;
	
	@NotBlank
	@Length(min = 2, max=25)
	String nome;
	
	
	public CategoriasDTO() {
		
	}

	public CategoriasDTO(Categorias obj) {
		
		this.id = obj.getId();
		this.nome = obj.getNome();
		
		
	}

	

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	
}
