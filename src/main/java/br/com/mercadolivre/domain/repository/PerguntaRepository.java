package br.com.mercadolivre.domain.repository;

import br.com.mercadolivre.domain.modelo.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {
}
