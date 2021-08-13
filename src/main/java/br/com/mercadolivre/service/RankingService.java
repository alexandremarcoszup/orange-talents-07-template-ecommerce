package br.com.mercadolivre.service;

import br.com.mercadolivre.domain.modelo.Compra;

public interface RankingService extends EventoCompraSucesso{

    void processa(Compra compra);
}
