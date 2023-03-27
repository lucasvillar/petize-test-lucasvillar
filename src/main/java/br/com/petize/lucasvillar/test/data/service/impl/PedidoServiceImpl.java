package br.com.petize.lucasvillar.test.data.service.impl;

import br.com.petize.lucasvillar.test.data.repository.PedidoRepository;
import br.com.petize.lucasvillar.test.data.service.AlterarStatusPedidoService;
import br.com.petize.lucasvillar.test.data.service.PedidoService;
import br.com.petize.lucasvillar.test.execption.InvalidRequestException;
import br.com.petize.lucasvillar.test.message.sender.AlterarPedidoStatusQueueSender;
import br.com.petize.lucasvillar.test.model.Pedido;
import br.com.petize.lucasvillar.test.model.StatusPedido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService, AlterarStatusPedidoService {

	private final Logger log = LoggerFactory.getLogger(PedidoServiceImpl.class);

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private AlterarPedidoStatusQueueSender alterarPedidoStatusQueueSender;

	@Override
	@Transactional
	public void alterarStatusPedido(Long pedidoId, StatusPedido novoStatusPedido) {
		this.pedidoRepository.updateStatusPedido(pedidoId, novoStatusPedido);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Pedido> findOneById(Long id) {
		return pedidoRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		pedidoRepository.deleteById(id);
	}

	@Override
	@Transactional
	public Pedido create(Pedido pedido) {
		if (pedido.getId() != null) {
			throw new InvalidRequestException();
		}

		if (!pedido.getProdutos().isEmpty()) {

		}
		return pedidoRepository.saveAndFlush(pedido);
	}


	/**
	 * Update a Pedido.
	 *
	 * @param pedido the entity to update partially.
	 * @return the persisted entity.
	 */
	@Transactional
	public Optional<Pedido> update(Pedido pedido) {
		log.debug("Request to partially update Pedido : {}", pedido);

		return pedidoRepository
				.findById(pedido.getId())
				.map(existingPedido -> {
					if (pedido.getDataPedido() != null) {
						existingPedido.setDataPedido(pedido.getDataPedido());
					}
					if (pedido.getProdutos() != null) {
						existingPedido.setProdutos(pedido.getProdutos());
					}
					if (pedido.getStatusPedido() != null && existingPedido.getStatusPedido() != null && pedido.getStatusPedido() != existingPedido.getStatusPedido()) {
						alterarPedidoStatusQueueSender.send(pedido);
					}

					return existingPedido;
				})
				.map(pedidoRepository::save);
	}

}
