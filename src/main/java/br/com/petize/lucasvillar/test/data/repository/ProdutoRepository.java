package br.com.petize.lucasvillar.test.data.repository;

import br.com.petize.lucasvillar.test.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {


}
