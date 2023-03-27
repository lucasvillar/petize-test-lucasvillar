package br.com.petize.lucasvillar.test.web.controller;

import br.com.petize.lucasvillar.test.data.service.ProdutoService;
import br.com.petize.lucasvillar.test.execption.NotFoundExecption;
import br.com.petize.lucasvillar.test.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping("/produto")
	private List<Produto> getAll() {
		return produtoService.findAll();
	}

	@GetMapping("/produto/{produtoId}")
	private Produto getById(@PathVariable("produtoId") long produtoId) {
		Optional<Produto> oneById = produtoService.findOneById(produtoId);
		return oneById.orElseThrow(NotFoundExecption::new);
	}

	@DeleteMapping("/produto/{produtoId}")
	private void deleteById(@PathVariable("produtoId") long produtoId) {
		produtoService.deleteById(produtoId);
	}

	@PostMapping("/produto")
	private Long create(@RequestBody Produto produto) {
		Produto produtoSaved = produtoService.create(produto);
		return produtoSaved.getId();
	}

	@PutMapping("/produto")
	private Produto update(@RequestBody Produto produto) {
		Optional<Produto> produtoUpdated = produtoService.update(produto);
		return produtoUpdated.get();
	}

}
