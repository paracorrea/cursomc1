package com.fernando.aulaspring.resources;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fernando.aulaspring.dominio.Categorias;
import com.fernando.aulaspring.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CatagoriaResources {

	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		
		Categorias obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	
	@RequestMapping(value="/todos", method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		
		List<Categorias> obj = service.buscarAll();
		return ResponseEntity.ok().body(obj);
		
	}
	
	
	
	
	
}
