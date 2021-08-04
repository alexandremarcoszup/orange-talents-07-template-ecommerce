package br.com.mercadolivre.domain.repository;

import br.com.mercadolivre.domain.modelo.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
