package com.fernando.aulaspring.resources;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fernando.aulaspring.dominio.Categorias;
import com.fernando.aulaspring.dominio.dao.CategoriasDTO;
import com.fernando.aulaspring.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CatagoriaResources {

	
	@Autowired
	private CategoriaService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		
		Categorias obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
		
	}
	
	
	@GetMapping("/")
	public ResponseEntity<List<CategoriasDTO>> findAll() {
		
		List<Categorias> list = service.buscarAll();
		List<CategoriasDTO> listDto = list.stream().map(obj -> new CategoriasDTO(obj)).collect(Collectors.toList());
			
		
		return ResponseEntity.ok().body(listDto);
		
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<CategoriasDTO>> findAllPage(
			@RequestParam(value="page", defaultValue="0")Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linePerPage,
			@RequestParam(value="orderBy", defaultValue="nome") String order,
			@RequestParam(value="direction", defaultValue="ASC") String direction)
	{
		
		Page<Categorias> list = service.buscarPorPag(page, linePerPage, order, direction);
		Page<CategoriasDTO> listDto = list.map(obj -> new CategoriasDTO(obj));
			
		
		return ResponseEntity.ok().body(listDto);
		
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriasDTO objDto) {
		Categorias obj = service.fromCategoriaDto(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@PutMapping("/{id}")
		public ResponseEntity<Void> update(@RequestBody Categorias obj, @PathVariable Integer id) {
		
		obj.setId(id);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		
		
		service.delete(id);
		
		return ResponseEntity.noContent().build();
		
	}
}
