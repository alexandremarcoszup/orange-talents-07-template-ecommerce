package br.com.mercadolivre.domain.repository;

import br.com.mercadolivre.domain.modelo.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
