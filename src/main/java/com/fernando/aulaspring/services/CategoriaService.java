package com.fernando.aulaspring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernando.aulaspring.dominio.Categorias;
import com.fernando.aulaspring.repositories.CategoriaRepository;
import com.fernando.aulaspring.services.exceptions.ObjectNotFoundException;


@Service
public class CategoriaService {


	@Autowired
	private CategoriaRepository repo;

	public Categorias find(Integer id) {
		
		Optional<Categorias> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objecto não encontrado id: " + id + ", tipo: " + Categorias.class.getName()));
	}

	public List<Categorias> buscarAll() {
		List<Categorias> lista = repo.findAll();
		return lista;
	}

	public Categorias insert(Categorias obj) {

		obj.setId(null);
		return repo.save(obj);

	}

	public Categorias update(Categorias obj) {
		// TODO Auto-generated method stub
		find(obj.getId());
		return repo.save(obj);
	}
}
