package com.fernando.aulaspring.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.fernando.aulaspring.dominio.Pedido;
import com.fernando.aulaspring.services.PedidoService;

@RestController
//@RequestMapping(value = "/clientes")
public class PedidoResource {

	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping("/pedido/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Pedido obj = pedidoService.buscar(id);
		return ResponseEntity.ok().body(obj);	}
	
	@GetMapping("/pedido/todos")
	public ResponseEntity<?> findAll() {
		
		List<Pedido> obj = pedidoService.buscarAll();
		return ResponseEntity.ok().body(obj)	;
				}
}
