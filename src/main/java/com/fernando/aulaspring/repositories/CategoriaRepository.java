package com.fernando.aulaspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fernando.aulaspring.dominio.Categorias;

// A interface repositorio instancia o banco de dados
public interface CategoriaRepository extends  JpaRepository<Categorias, Integer>{

	//Categorias deleteById(Categorias obj);

	

	
	
	
}
