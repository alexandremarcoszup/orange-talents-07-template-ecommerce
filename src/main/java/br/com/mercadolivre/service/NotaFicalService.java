package br.com.mercadolivre.service;

import br.com.mercadolivre.domain.modelo.Compra;

public interface NotaFicalService extends EventoCompraSucesso{

    void processa(Compra compra);
}
