package br.com.petize.lucasvillar.test.message.consumer;


import br.com.petize.lucasvillar.test.data.service.AlterarStatusPedidoService;
import br.com.petize.lucasvillar.test.model.Pedido;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class StatusPedidoUpdateQueueConsumer {

	@Autowired
	private AlterarStatusPedidoService alterarStatusPedidoService;

	@RabbitListener(queues = {"${app.queues.alterarStatusPedido}"})
	public void receive(@Payload Pedido pedido) {
		alterarStatusPedidoService.alterarStatusPedido(pedido.getId(), pedido.getStatusPedido());
	}

}
