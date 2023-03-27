package br.com.petize.lucasvillar.test.message.sender;


import br.com.petize.lucasvillar.test.model.Pedido;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AlterarPedidoStatusQueueSender {


	@Value("${app.queues.alterarStatusPedido}")
	private String queueName;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void send(Pedido pedido) {
		rabbitTemplate.convertAndSend(queueName, pedido);
	}


}
