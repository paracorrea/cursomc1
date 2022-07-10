package com.fernando.aulaspring.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/categories")
public class CatagoriaResources {

	@RequestMapping(method=RequestMethod.GET)
	public String listar() {
		
		return "Rest funcionando...";
		
	}
	
}
