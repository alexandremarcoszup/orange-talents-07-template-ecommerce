package br.com.mercadolivre.service;

import br.com.mercadolivre.domain.modelo.Compra;
import br.com.mercadolivre.domain.modelo.Pergunta;

public interface EmailSenderService {

    void sendEmailPergunta(Pergunta pergunta);

    void sendEmailCompra(Compra compra);

    void sendEmailFinalizacaoCompra(Compra compra);
}
