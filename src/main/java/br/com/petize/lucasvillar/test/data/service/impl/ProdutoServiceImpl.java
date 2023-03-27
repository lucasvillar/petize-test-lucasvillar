package br.com.petize.lucasvillar.test.data.service.impl;

import br.com.petize.lucasvillar.test.data.repository.ProdutoRepository;
import br.com.petize.lucasvillar.test.data.service.ProdutoService;
import br.com.petize.lucasvillar.test.execption.InvalidRequestException;
import br.com.petize.lucasvillar.test.model.Produto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class ProdutoServiceImpl implements ProdutoService {

	private final Logger log = LoggerFactory.getLogger(PedidoServiceImpl.class);

	@Autowired
	private ProdutoRepository repository;


	@Override
	@Transactional(readOnly = true)
	public List<Produto> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Produto> findOneById(@NotNull Long id) {
		return repository.findById(id);
	}

	@Override
	public void deleteById(@NotNull Long id) {
		repository.deleteById(id);
	}

	@Override
	public Produto create(@Valid Produto produto) {
		if (produto.getId() != null) {
			throw new InvalidRequestException();
		}

		return repository.saveAndFlush(produto);
	}


	/**
	 * Update a Produto.
	 *
	 * @param produto the entity to update partially.
	 * @return the persisted entity.
	 */
	public Optional<Produto> update(Produto produto) {
		log.debug("Request to partially update Produto : {}", produto);

		return repository
				.findById(produto.getId())
				.map(existingProduto -> {
					if (produto.getDescricao() != null) {
						existingProduto.setDescricao(produto.getDescricao());
					}
					if (produto.getDescricao() != null) {
						existingProduto.setPreco(produto.getPreco());
					}

					return existingProduto;
				})
				.map(repository::save);
	}


}
