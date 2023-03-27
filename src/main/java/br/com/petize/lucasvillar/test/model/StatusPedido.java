package br.com.petize.lucasvillar.test.model;

import lombok.Getter;

public enum StatusPedido {

	PENDENTE("P"),
	PROCESSANDO("PP"),
	CONCLUIDO("C");

	@Getter
	private final String codigo;

	StatusPedido(String codigo) {
		this.codigo = codigo;
	}
}
