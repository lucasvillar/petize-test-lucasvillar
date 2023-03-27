package br.com.petize.lucasvillar.test.data.service;

import br.com.petize.lucasvillar.test.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface SimpleCrudService<T, ID> {

	List<T> findAll();


	Optional<T> findOneById(ID id);

	void deleteById(ID id);

	T create(T entity);

	Optional<T> update(T entity);


}
