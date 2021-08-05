package br.com.mercadolivre.domain.repository;

import br.com.mercadolivre.domain.modelo.Caracteristica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaracteristicaRepository extends JpaRepository<Caracteristica, Long> {
}
