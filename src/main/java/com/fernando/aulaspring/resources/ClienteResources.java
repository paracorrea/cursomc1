package com.fernando.aulaspring.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fernando.aulaspring.dominio.Cliente;
import com.fernando.aulaspring.services.ClienteService;

@RestController
//@RequestMapping(value = "/clientes")
public class ClienteResources {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/cliente/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Cliente obj = clienteService.buscar(id);
		return ResponseEntity.ok().body(obj);	}
	
	@GetMapping("/cliente/todos")
	public ResponseEntity<?> findAll() {
		
		List<Cliente> obj = clienteService.buscarAll();
		return ResponseEntity.ok().body(obj)	;
				}
}
