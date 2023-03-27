package br.com.petize.lucasvillar.test.data.service;

import br.com.petize.lucasvillar.test.model.StatusPedido;

public interface AlterarStatusPedidoService {

	void alterarStatusPedido(Long pedidoId, StatusPedido novoStatusPedido);


}
