package br.com.mercadolivre.service;

import br.com.mercadolivre.domain.modelo.Pergunta;

public interface EmailSender {

    void sendEmailPergunta(Pergunta pergunta);
}
