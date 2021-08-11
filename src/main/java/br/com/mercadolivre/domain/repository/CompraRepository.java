package br.com.mercadolivre.domain.repository;

import br.com.mercadolivre.domain.modelo.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra, Long> {
}
