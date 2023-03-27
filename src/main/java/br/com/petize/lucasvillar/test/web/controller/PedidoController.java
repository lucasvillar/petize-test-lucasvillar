package br.com.petize.lucasvillar.test.web.controller;

import br.com.petize.lucasvillar.test.data.service.PedidoService;
import br.com.petize.lucasvillar.test.execption.NotFoundExecption;
import br.com.petize.lucasvillar.test.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@GetMapping("/pedido")
	private List<Pedido> getAll() {
		return pedidoService.findAll();
	}

	@GetMapping("/pedido/{pedidoId}")
	private Pedido getById(@PathVariable("pedidoId") long pedidoId) {
		Optional<Pedido> oneById = pedidoService.findOneById(pedidoId);
		return oneById.orElseThrow(NotFoundExecption::new);
	}

	@DeleteMapping("/pedido/{pedidoId}")
	private void deleteById(@PathVariable("pedidoId") long pedidoId) {
		pedidoService.deleteById(pedidoId);
	}

	@PostMapping("/pedido")
	private Long create(@RequestBody Pedido pedido) {
		Pedido pedidoSaved = pedidoService.create(pedido);
		return pedidoSaved.getId();
	}

	@PutMapping("/pedido")
	private Pedido update(@RequestBody Pedido pedido) {
		Optional<Pedido> pedidoUpdated = pedidoService.update(pedido);
		return pedidoUpdated.get();
	}


}
