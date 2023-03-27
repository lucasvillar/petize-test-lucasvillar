package br.com.petize.lucasvillar.test.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_pedido")
public class Pedido implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private LocalDateTime dataPedido;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "tb_pedido_produto",
			joinColumns = @JoinColumn(
					name = "pedido_id", referencedColumnName = "id"
			),
			inverseJoinColumns = @JoinColumn(
					name = "produto_id", referencedColumnName = "id"
			)
	)
	private Set<Produto> produtos = new HashSet<>();

	private StatusPedido statusPedido;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Pedido pedido = (Pedido) o;
		return Objects.equals(id, pedido.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
