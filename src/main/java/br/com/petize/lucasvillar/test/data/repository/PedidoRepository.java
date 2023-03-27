package br.com.petize.lucasvillar.test.data.repository;

import br.com.petize.lucasvillar.test.model.Pedido;
import br.com.petize.lucasvillar.test.model.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	@Modifying
	@Query("update Pedido set statusPedido = ?2 where id = ?1")
	void updateStatusPedido(Long pedidoId, StatusPedido statusPedido);


}
