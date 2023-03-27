package br.com.petize.lucasvillar.test.data.conveter;

import br.com.petize.lucasvillar.test.model.StatusPedido;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter(autoApply = true)
public class StatusPedidoConveter implements AttributeConverter<StatusPedido, String> {
	@Override
	public String convertToDatabaseColumn(StatusPedido statusPedido) {
		return statusPedido != null ? statusPedido.getCodigo() : null;
	}
	@Override
	public StatusPedido convertToEntityAttribute(String dbData) {
		return Arrays.stream(StatusPedido.values())
				.sequential()
				.filter(statusPedido -> statusPedido.getCodigo().equals(dbData))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
}
